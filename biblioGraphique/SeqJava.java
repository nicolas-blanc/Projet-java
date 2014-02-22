

/**************** Sequences de Code JAVA ***************/


/*****************************************/
/* Lecture des lignes d'un fichier texte */
/*****************************************/


public void lectureLignesFichier(){

try  {
	BufferedReader in = new BufferedReader(
				new FileReader("XXXXX.txt"));
	String ligne;
	while ((ligne= in.readLine()) != null)  {
		
		/*** traitement de la chaine ch ***/
	}
	in.close();
}

catch (IOException e) {
 	System.out.println("$$$$$ PB de Lecture dans le fichier XXXXX ");
	System.out.println();
}


} //  lectureLignesFichier;



/*****************************************/
/*   Extraction des mots d'une chaine    */
/*****************************************/

String uneChaine = "TOTO  TUTU TITI";

StringTokenizer parser = new StringTokenizer(uneChaine);

	while (parser.hasMoreTokens())  {

	    traitement-du-Mot(parser.nextToken());

	}


