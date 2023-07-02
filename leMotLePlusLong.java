import java.util.Scanner;

public class leMotLePlusLong{
  //m�thode dichotomique qui renvoi true si le mot se trouve dans le Dico
  static boolean rechercheDichotomie(char[] [] tab , char [] mot){
    int debut =0 ;
    int fin = tab.length-1 ;
    int mid = (debut+fin)/2;
    while(true){
      if(debut+1==fin||debut==fin){ 
        if(ordreMots(mot,tab[debut])==0||ordreMots(mot,tab[fin])==0) return true;
        else return false;
      }
      if(ordreMots(mot,tab[mid])==0){
        return true;
      }
      if(ordreMots(mot,tab[mid])==1){
        fin = mid;
      }
      if(ordreMots(mot,tab[mid])==-1){
        debut = mid;
      }
      mid = (debut+fin)/2;
    }
  }
  //M�thode pour Comparer les mots. renvoie 1 si mot1 devant mot2 , 0 si les mots sont identiques -1 sinon
  static int ordreMots(char[] mot1, char[] mot2){
    int k = min(mot1.length, mot2.length);
    for(int i= 0 ; i< k ; i++) {
      if(mot1[i]>mot2[i]) return -1;
      if(mot1[i]<mot2[i]) return 1;
    }
   if(mot2.length==mot1.length) return 0;
   if(mot2.length==k) return -1;
   return 1;
  }
  //recherche dans le Dico, les mots du tab combinaisons jusqu'� l'indice "fronti�re" :  dernier. et affiche toutes  
  public static boolean verifDico2(char[] [] tab, int dernier, char [] [] Dico){
    boolean trouve = false;
    boolean [] appartientDico = new boolean [dernier] ;
    for(int i = 0 ; i< dernier ; i++){
      if(rechercheDichotomie(Dico,tab[i])){
        appartientDico[i] = true ;
        trouve = true ; 
      }
    }
    if(trouve){
      System.out.println("Voici le(s) mot(s) le(s) plus long(s) possible avec ce tirage (taille "+tab[0].length+" ) :") ;
      for(int i=0 ; i<appartientDico.length ; i++){
        if(appartientDico[i]) System.out.println(tab[i]);
      } 
    }
    return trouve ;
  }
  //recherche la taille des mots les plus long qu'on peut faire avec le tirage en commen�ant par la 10 et on arr�te d�s qu'on a trouv�
  public static void plusLongPossible(char [] [] Combinaisons , char [] lettresTirage, int [] compteLettres, char [] [] Dico) {
    boolean trouve = false ;
    int taille = 10;
      while(!trouve && taille >= 1){
      char [] motPossible = new char[taille];
      int [] indice = {0}; 
      remplirTabCombinaisons(Combinaisons,indice,motPossible,lettresTirage,compteLettres,taille);  
      if(verifDico2(Combinaisons,indice[0],Dico)){
        trouve = true ;
      }
      taille--; 
    }
  }
  //Compl�te le tableau incomplet Combinaisons avec tous les mots possibles de taille donn�e en incr�mentant l'indice "fronti�re"
  public static void remplirTabCombinaisons ( char [] [] Combinaisons, int [] indice, char [] motPossible ,char [] lettresTirage, int[]compteLettres, int taille){
    if(taille==0){
      Combinaisons[indice[0]] = motPossible;
      indice[0]++;
    }
    if(taille>0){
      for(int i=0 ; i<compteLettres.length ; i++){
        if(compteLettres[i]>0) {
          int n = motPossible.length ;
          char [] motPossible2 = copyTabChar(motPossible);
          motPossible2[n-taille]= lettresTirage[i];
          int [] copyCompte= copyTabInt(compteLettres);
          copyCompte[i]--;
          remplirTabCombinaisons(Combinaisons, indice, motPossible2, lettresTirage, copyCompte,taille-1);
        } 
      } 
    }
}
//calcul de factorielle pour un entier
  public static int factorielle ( int n){
    if(n==0) return 1 ;
    return n*factorielle(n-1);
  }
//calcul taille Tab Combinaisons - d�terminine le nombre maximal de permutations possibles avec 10 lettres en �vitant les permutations identiques.
  public static int tailleTab(int [] compteLettres){
    int maxPermutations = factorielle(10);
    for(int i=0; i<compteLettres.length ; i++){
      maxPermutations = maxPermutations/factorielle(compteLettres[i]) ;
    }
    return maxPermutations;
  }
//compte le nombre d'occurences d'un chract�res dans un tableau
 public static int compteOccurrences(char c, char[] t) {
  int res=0;
  for (int i=0; i<t.length; i++) {
   if(c==t[i])
    res++;
  }
  return res;
 }
 // Retourne un tableau donnant le nombre d'occurrences dans le tirage de chacune de ses lettres diff�rentes. 
 public static int [] compteOccurrencesLettres(char [] lettresTirage, char [] tirage){
  int[] nbOccurrences = new int [lettresTirage.length];
  for(int i=0 ; i<lettresTirage.length; i++){
   nbOccurrences[i]= compteOccurrences(lettresTirage[i], tirage);
  }
  return nbOccurrences;
 }
 
public static char[] donnerTirage(){
 double[] frequences = {
     0.084641116045612,
     0.097550346533667,
     0.134264256131715,
     0.175931501299866,
     0.339787563705305,
     0.352412789645191,
     0.366356662429924,
     0.37898710353533,
     0.454338255487703,
     0.458247915903501,
     0.461526843221907,
     0.517821090574744,
     0.547529673593674,
     0.620002807215,
     0.677444355180383,
     0.70566493929644,
     0.71299279223788,
     0.781799811286431,
     0.855650833407438,
     0.922800098125308,
     0.974199670376239,
     0.986838818540602,
     0.98881203071521,
     0.993095137557963,
     0.998288765757996,
     1};
 char[] res=new char[10];
 for (int i=0; i<res.length; i++){
     double ausort = Math.random();
     int j=0;
     while(frequences[j]<ausort)
  j++;
     res[i] = (char)('A'+j);
 }
 java.util.Arrays.sort(res);
 return res;
    }


    public static char[][] getDictionnaire(){
 char[][] res;
 char[][] temp = new char[500000][];
 int nbMots = 0;
 try{
     java.io.FileReader fr = new java.io.FileReader("dico.txt");
     java.io.BufferedReader br = new java.io.BufferedReader(fr);
     String ligne = br.readLine();
     while(ligne != null){
  temp[nbMots] = ligne.trim().toCharArray();
  nbMots++;
  ligne = br.readLine();
     }
     br.close();
 }
 catch(java.io.FileNotFoundException e){
     throw new RuntimeException("Fichier dico_nfa031.txt non trouvé");
 }
 catch(java.io.IOException e){
     throw new RuntimeException("Problème à la lecture du fichier");
 }
 res = new char[nbMots][];
 for (int i=0; i<nbMots; i=i+1){
     res[i]=temp[i];
 }
 return res;
    }
//on recommence le jeu si l'utilisateur tape la lettre o s'il entre la lettre n le programme s'arr�te
public static boolean recommenceJeu(){
  Scanner sc = new Scanner(System.in);
  boolean recommence = true ;
  while(recommence){
  System.out.println("voulez vous recommencer le jeu ? r�pondez OUI par la lettre O et NON par la lettre n");
  char rep = sc.nextLine().charAt(0);
  if(rep=='o' || rep == 'O') return true;
  if(rep=='n'|| rep=='N') return false ;
  else System.out.println("Entr�e invalide : entrez soit la lettre 'O' ou la lettre 'N'");
  }
  return false; 
}
//On donne les explications si le joueur a perdu le jeu soit le mot n'est pas dans le dico et/ou le mot correspond pas au tirage. S'il a gagn� on donne la taille du mot.
  public static void resultatJeu(boolean verifDico, boolean perdu, char [] lettres, int [] compteManques ){
    if(!verifDico) System.out.println("Votre mot NE se trouve PAS dans le dictionnaire que nous poss�dons");

    if(perdu) {
      System.out.println("Votre mot NE correspond PAS au tirage : ");
      for(int i=0 ; i<compteManques.length ; i++){
        if(compteManques[i]>0){
          System.out.println("Il manque "+compteManques[i]+" occurrence(s) de la lettre "+lettres[i]);
        }
      }
    }
    if(verifDico && !perdu) System.out.println("Votre mot fait "+lettres.length+" lettres.");
  }
//renvoie true si 2 monts sont identiques false sinon
  public static boolean compareMot( char [] mot1 , char[] mot2){
     if(mot1.length!=mot2.length){
       return false;
     }else{
       for(int i=0 ; i< mot1.length ; i++){
         if(mot1[i]!=mot2[i]) return false;
       }
       return true;
     }
  }
//recherche lin�aire d'un mot dans le dictionnaire
  public static boolean verifDico (char[] mot, char[][] Dico){
    for(int i=0 ; i< Dico.length ;i++){
      if(compareMot(mot,Dico[i])) return true;
    }
  return false;
  }
//renvoie true si un charact�re appartient au tableau false sinon
 public static boolean appartient(char c, char[] tabc) {
  for(int i=0 ; i< tabc.length; i++){
   if (c==tabc[i])
    return true;
  }
  return false;
 }
//renvoie un tableau de charact�re sans les occurences multiples
 public static char[] ensembleCharDansTab(char[] t){
  if (t==null | t.length==0)
   throw new IllegalArgumentException();
  char [] sansdoublons = new char[t.length];
  sansdoublons[0]=t[0];
  int nb=1;
  for (int i=1; i<t.length; i++) {
   if (!appartient(t[i],sansdoublons)) {
    sansdoublons[nb]=t[i];
    nb++;
   }
  }
  char [] res = new char[nb];
  for(int i=0; i<nb;i++) {
   res[i]=sansdoublons[i];
  }
  return res;
 }
//renvoie l'indice si la lettre se trouve dans le tirage, et met # � la place de la lettre. renvoie -1 sinon
  public static int compareLettre(char lettre , char[] copyTirage){
      for(int i=0 ; i< copyTirage.length ; i++){
        if(copyTirage[i]==lettre) {
          copyTirage[i]='#';
          return i;
        }
      } 
    return -1 ; 
  }
//si la lettre se trouve dans le tirage cette m�thode affiche l'indice correspondant. le tiret est affich� � l'identique.
//Si la lettre ne se trouve pas ou d�j� �t� utilis�e on affiche X � la place et on modifie le contenu du tab perdu en true. 
  public static void correspondances(char[] mot, char[]tirage, boolean[] perdu, char[] lettres, int [] compteManques){
    System.out.println(mot);
    for(int i= 0 ; i< mot.length ; i++){
      int res = compareLettre(mot[i],tirage);
      if(mot[i]=='-') System.out.print('-');
      else if(res==-1){ 
        perdu[0] = true ;
        System.out.print('X');
        for(int j=0 ; j<lettres.length ; j++){
          if(lettres[j]==mot[i]) compteManques[j]++ ; 
        }
      }    
        else System.out.print(res);
    }
    System.out.println();
  }
//renvoie le mot sous forme String en transformant en lettres CAPITALES. seules les lettres et le tiret sont autoris�s. 
  public static String donnerMot(){
    Scanner sc = new Scanner(System.in);
    boolean recommence = true;
    String mot="";
    while(recommence){
      System.out.println("Entrez un mot : ");
      mot = sc.nextLine();
      if(mot.equals("")||mot.equals(" ")||mot.equals("  ")){
        System.out.println(" Erreur : mot vide ! ");
      }else{
      int compteur = 0; 
      for(int i=0 ; i< mot.length(); i++){
        if((mot.charAt(i)>='A'&& mot.charAt(i)<='Z')||(mot.charAt(i)>='a'&&mot.charAt(i)<='z')||mot.charAt(i)=='-') compteur++;
      }
      if(compteur==mot.length()) return mot.toUpperCase();
      else System.out.println("Le mot invalide ou comporte des caract�res non autoris�s. R�crivez votre mot en lettres sans accents");
      }
    }
   return mot;
  }
// Partie - METHODES AUXILIAIRES
  //m�thode qui copie un tableau d'entiers
  public static int [] copyTabInt( int [] tab){
     int [] tabNew = new int [tab.length];
     for(int i=0 ; i<tab.length ; i++)
       tabNew[i] = tab[i];
    return tabNew;
  }
  //m�thode qui copie un tableau de charact�res
  public static char[] copyTabChar( char [] tab){
     char [] tabNew = new char [tab.length];
     for(int i=0 ; i<tab.length ; i++)
       tabNew[i] = tab[i];
    return tabNew;
  }
 //cette m�thode affiche un tableau de charact�res
  public static void afficheTab( char [] tab){
    for(int i=0 ; i<tab.length ; i++)
      System.out.print(tab[i]);
    System.out.println();
  }
 //cette m�thode affiche les indices puis en bas le contenu d'un tableau de charact�res
   public static void afficheIndice (char[] tirage){
   for(int i=0; i<tirage.length;i++)
      System.out.print(i);
    System.out.println();
    afficheTab(tirage);
 }
 //cette m�thode renvoie la valeur minimale entre deux entiers
   public static int min( int m , int n ){
    if(m<=n) return m ; 
    else return n ; 
  }
  
  public static void main(String[] args) {
    char [][] Dico = getDictionnaire();
    boolean recommence = true;
    while(recommence){
    char [] tirage = donnerTirage();
    System.out.print("Votre tirage est : ");
    afficheTab(tirage);
    char [] copyTirage = copyTabChar(tirage);
    char [] mot = donnerMot().toCharArray();
    char [] lettres =  ensembleCharDansTab(mot);
    int [] compteManques= new int[lettres.length];
    boolean [] perdu = {false}; 
    correspondances(mot,copyTirage,perdu,lettres,compteManques);
    afficheIndice(tirage);
    resultatJeu(verifDico(mot,Dico), perdu[0] , lettres, compteManques);
    //d�but code de la recherche du mot le plus long possible
    char[] lettresTirage = ensembleCharDansTab(tirage);
    int [] compteLettres = compteOccurrencesLettres(lettresTirage,tirage);
    char [] [] Combinaisons = new char [tailleTab(compteLettres)] [] ;
    plusLongPossible(Combinaisons ,lettresTirage,compteLettres,Dico);
    recommence = recommenceJeu(); 
    }
  }
}