/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller0;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static javafx.beans.binding.Bindings.not;

/**
 *
 * @author S41-70
 */
public class Taller0 {
    /*** method to load the data from the clients file
    *
    */ 
    public static int leerClientes(String[] nombres,String[] apellidos, String[]ruts,String[]contraseñas,double[]saldos) throws IOException{
        File file = new File("clientes.txt");
        Scanner arch = new Scanner(file); 
        int i = 0;
        while ( arch.hasNextLine()){
            String linea = arch.nextLine();
            String [] partes = linea.split(",");
            nombres[i] = partes[0];
            apellidos[i] = partes[1];
            ruts[i] = partes[2];
            contraseñas[i] = partes[3] ;
            saldos[i] = Double.parseDouble(partes[4]);
            i ++; 
        }
        arch.close(); 
        return i;
    }
    /*** method to load the data from the status file
    *
    */ 
    public static int leerStatus( String[]ruts,String[]estadoPase, int numClientes) throws IOException{
        File file = new File("status.txt");
        Scanner arch1 = new Scanner(file); 
        int i = 0;
        while ( arch1.hasNextLine()){
            String linea = arch1.nextLine();
            String [] partes = linea.split(",");
            String rut = partes[0];
            //hacer el for para luego ir comparando con el vector ruts de clientes
            for(int j = 0; j<numClientes;j++){
                //si son iguales entonces agrega el estado en dicha posision
                if(ruts[j].equals(rut)){
                    estadoPase[j] = partes[1];
                }
            }
            i ++; 
        }
        arch1.close(); 
        return i;
    }
    /*** method to load the data from the movie file
    *
    */ 
    public static int leerPeliculas(String[] peliculas,String[] tipoPeliculas,double[]recaudaciones,int [] salas,String [] funciones) throws IOException{
        File file = new File("peliculas.txt");
        Scanner arch2 = new Scanner(file); 
        int i = 0;
        while ( arch2.hasNextLine()){
            String linea = arch2.nextLine();
            String [] partes = linea.split(",");
            String pelicula = partes[0];
            String t = partes[1];
            double rec = Double.parseDouble(partes[2]);
            for(int j = 3;j<partes.length;j+=2){
                peliculas[i]= pelicula;
                tipoPeliculas[i] = t;
                recaudaciones[i] = rec;
                int n = Integer.parseInt(partes[j]);
                String f =  partes[j+1];
                salas[i] = n;
                funciones[i] = f;
                i++;
                
            }
            
            i ++; 
        }
        arch2.close(); 
        return i;
    }
    /*** method to print the client menu with the available options
    */ 
    public static void imprimirMenuCliente() {
	System.out.println("---------------------------menu---------------------------");
	System.out.println("opciones: (ingrese la letra)");
	System.out.println("A: despliega la opción comprar entrada");
        System.out.println("B: despliega la opción informacion usuario");
        System.out.println("C: despliega la opción devolucion entrada");
        System.out.println("D: despliega la opción cartelera");
	System.out.println("E: Salir");
	System.out.println("----------------------------------------------------------");
        
    }
    /** method to print the administrator menu with the available options
    */ 
    public static void imprimirMenuAdmin() {
	System.out.println("---------------------------menu---------------------------");
	System.out.println("opciones: (ingrese la letra)");
	System.out.println("A: despliega la opción taquilla");
        System.out.println("B: despliega la opción informacion cliente");
	System.out.println("C: Salir");
	System.out.println("----------------------------------------------------------");
    }
    /*** method that allows to format the ru of the form with period and 
    * hyphen even if it is entered in different ways
     * @param rut
     * @return 
    */ 
    public static String formatearRut(String rut){
        int cont = 0;
        String format;
        rut= rut.replace(".", "");
        rut = rut.replace("-", "");
        format= "-" + rut.substring(rut.length()-1);
        for(int i = rut.length()-2; i>=0;i--){
          format=rut.substring(i,i+1)+format;
          cont++;
          if(cont==3 && i!=0){
              format= "."+format;
              cont=0;
          }
        }
        return format;
    }
    /*** method to print start menu with available options
    */ 
    public static void imprimirMenu() {
	System.out.println("---------------------------menu---------------------------");
	System.out.println("opciones: (ingrese como RF#)");
	System.out.println("RF1: iniciar sesion ");
        System.out.println("RF2: registrarse al sistema");
	System.out.println("RF3: Salir");
	
    }
    /*** method to say goodbye after closing the start menu
    */ 
    public static void RF2() {
        System.out.print("Gracias,Tenga un Buen Dia ");
	System.out.print("Adios :D");
    }
    /*** method to implement the menu with all the workshop requirements
     * @param nombres
     * @param apellidos
     * @param ruts
     * @param contraseñas
     * @param saldos
     * @param numClientes
     * @param peliculas
     * @param tipoPeliculas
     * @param recaudaciones
     * @param salas
     * @param funciones
     * @param numPeliculas
    */ 
    public static void Menu(String[] nombres,String[] apellidos, String[]ruts,String[]contraseñas,double[]saldos,int numClientes,String[] peliculas,String[] tipoPeliculas,double[]recaudaciones,int [] salas,String [] funciones,int numPeliculas){
        imprimirMenu();
        System.out.print("Ingrese opcion: ");
        Scanner s = new Scanner(System.in);
        String opcion = s.next();
        boolean salir = false;
        while(!opcion.equals("RF3")){
            switch(opcion){
            case "RF1":
                /*** method to implement the admin menu with all the workshop requirements
                 * 
                 */
                System.out.print("Ingrese rut: ");
                s = new Scanner(System.in);
                String rut = s.nextLine();
                String nuevoRut = formatearRut(rut);
                System.out.print("Ingrese contrasenna: ");
                s = new Scanner(System.in);
                String clave = s.nextLine();    
                if(rut.equals("ADMIN") && clave.equals("ADMIN")){
                    imprimirMenuAdmin();
                    System.out.print("Ingrese opcion: ");
                    String opcion3 = s.next();
                    while(!opcion3.equals("C")){
                        switch(opcion3){
                        case "A":
                            break;
                        case "B":
                            System.out.print("Ingrese rut de cliente: ");
                            String rutCliente = s.next();
                            String nRut = formatearRut(rutCliente);
                            for(int k= 0 ;k<numClientes;k++){
                                if(ruts[k].equals(nRut)){
                                    System.out.println("----------------SUS DATOS ----------------");
                                    System.out.println("Nonbre: "+ nombres[k]+"\n"+"Apellido: "+apellidos[k]+"\n"+"Saldo: "+saldos[k]);
                                    System.out.println("----------------ENTRADAS COMPRADAS----------------");
                                }
                            }
                            break;
                        case "C":
                            salir = true;
                            break;
                        }
                        System.out.println( "\n" );
                        imprimirMenuAdmin();
                        System.out.print("Ingrese opcion: ");
                        opcion3 = s.next();
                    }
                }
                else{
                    /*** method to implement the menu with all the workshop requirements
                     * 
                     */
                    for(int i=0;i<numClientes;i++){
                        if(ruts[i].equals(nuevoRut) && contraseñas[i].equals(clave)){
                            imprimirMenuCliente();
                            System.out.print("Ingrese opcion: ");
                            String opcion1 = s.next();
                            while(!opcion1.equals("E")){
                                switch(opcion1){
                                case "A":
                                    System.out.println("------------------------------------------");
                                    for(int t=0;t<numPeliculas;t++){
                                        if(peliculas[t] != null){
                                            System.out.println(peliculas[t]+" "+"horario"+" "+salas[t]+" "+funciones[t]);
                                            
                                        }
                                        
                                    }
                                    System.out.println("\n");
                                    Scanner P = new Scanner(System.in);
                                     
                                    System.out.print("Ingrese nombre de la pelicula: ");
                                    String pe = P.next();
                                    for(int j = 0;j<numPeliculas;j++){
                                        
                                        System.out.println(peliculas[buscar(pe,peliculas,numPeliculas)]);
                                        
                                        
                                    }
                                    break;
                                case "B":
                                    for(int k= 0 ;k<numClientes;k++){
                                        if(ruts[k].equals(nuevoRut)){
                                        System.out.println("----------------SUS DATOS ----------------");
                                        System.out.println(ruts[k]+" "+ nombres[k]+" "+apellidos[k]+" "+saldos[k]);
                                        System.out.println("----------------FUNCIONES----------------");
                                        }
                            
                                    }
                                    break;
                                case "C":
                                    break;
                                case "D":
                                    
                                    for(int j = 0;j<numPeliculas;j++){
                                        System.out.println("----------------------------------------");
                                        if(peliculas[j] != null){
                                            System.out.println(peliculas[j]);
                                            System.out.println("----------------HORARIO----------------");
                                            if(salas[j] != 0 && funciones[j]!= null ){
                                                System.out.println(salas[j] + " "+funciones[j]+"\n");   
                                            } 
                                        }
                                        
                                    }
                                    break;
                                case "E":
                                    salir = true;
                                    break;
                                }
                                System.out.println( "\n" );
                                imprimirMenuCliente();
                                System.out.print("Ingrese opcion: ");
                                opcion1 = s.next();
                            }
                            
                        }
                        else{
                            System.out.println("Rut y/o Contraseñas incorrectos ");
                        }
                    }    
                }
                break;
            case "RF2":
                /*** method to implement the creation of a new user with all the requirements of the workshop 
                 * 
                 */   
                s = new Scanner(System.in);
                System.out.print("Ingrese nombre: ");
                String nombre1 = s.nextLine();
                System.out.print("Ingrese apellido: ");
                String apellido1 = s.nextLine();
                System.out.print("Ingrese rut: ");
                String ru = s.nextLine();
                String rut1 = formatearRut(ru);
                System.out.print("Ingrese contrasenna: ");
                String clave1 = s.nextLine();
                System.out.print("Ingrese saldo: ");
                double saldo1 = s.nextDouble();
                nombres[numClientes+1] = nombre1;
                apellidos[numClientes+1] = apellido1;
                ruts[numClientes+1] = rut1;
                contraseñas[numClientes+1] =clave1;
                saldos[numClientes+1] = saldo1;
                break;
            case "RF3":
                salir = true;
                break;
            }
            System.out.println( "\n" );
            imprimirMenu();
            System.out.print("Ingrese opcion: ");
            opcion = s.next();
            
        }
        RF2();
    }
    public static void rellenoMatriz(int [][] matrizAsientos1M,int [][] matrizAsientos1T,int [][] matrizAsientos2M,int [][] matrizAsientos2T,int [][] matrizAsientos3M,int [][] matrizAsientos3T){
        for(int p = 0;p<4;p++){
            for(int q = 0;q<5;q++){
                matrizAsientos1M [p][q] = 1;
                matrizAsientos1T [p][q] = 1;
                matrizAsientos2M [p][q] = 1;
                matrizAsientos2T [p][q] = 1;
                matrizAsientos3M [p][q] = 1;
                matrizAsientos3T [p][q] = 1;  
            }    
        }
        for(int p =0;p<4;p++){
            for(int q = 25;q<30;q++){
                matrizAsientos1M [p][q] = 1;
                matrizAsientos1T [p][q] = 1;
                matrizAsientos2M [p][q] = 1;
                matrizAsientos2T [p][q] = 1;
                matrizAsientos3M [p][q] = 1;
                matrizAsientos3T [p][q] = 1;   
            }    
        }
        
    }
    public static int buscar(String nomP,String[]peliculas,int numPeliculas){
        int i = 0;
        while(i<numPeliculas && peliculas[i].equals(nomP)){
            i++;
        }
        if(i== numPeliculas){
            return -1;
        }
        else{
            return i;
        }
    }
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        /*** vector initialization of the customers text file
         * 
         */
        String [] nombres = new String [100];
        String [] apellidos = new String [100];
        String [] ruts = new String [100];
        String [] contraseñas = new String [100];
        double [] saldos = new double [100];
        
        /*** vector initialization of the status text file
         * 
         */       
        String []estadoPase = new String [100];
        
        /*** vector initialization of the movie text file
         * 
         */
        String letras [] = {"A","B","C","D","E","F","G","H","I","J"}; 
        String [] peliculas = new String [100];
        String [] tipoPeliculas = new String [100];
        double [] recaudaciones = new double [100];
        int [] salas = new int [100];
        String [] funciones = new String [100];
        
        /*** initialization of arrays for seats
         * 
         */
        int [][] matrizAsientos1M = new int [10][30];
        int [][] matrizAsientos1T = new int [10][30];
        int [][] matrizAsientos2M = new int [10][30];
        int [][] matrizAsientos2T = new int [10][30];
        int [][] matrizAsientos3M = new int [10][30];
        int [][] matrizAsientos3T = new int [10][30];
        
        int numClientes =  leerClientes(nombres,apellidos,ruts,contraseñas,saldos);

        int numStatus = leerStatus(ruts,estadoPase,numClientes);
        
        int numPeliculas = leerPeliculas(peliculas,tipoPeliculas,recaudaciones,salas,funciones);
        
        rellenoMatriz(matrizAsientos1M,matrizAsientos1T,matrizAsientos2M,matrizAsientos2T,matrizAsientos3M,matrizAsientos3T);
        
        
        //for(int t=0; t<30;t++){
          //System.out.println(matrizAsientos[0][t]+" "+matrizAsientos[1][t]+" "+matrizAsientos[2][t]+" "+matrizAsientos[3][t]+" "+matrizAsientos[4][t]+" "+matrizAsientos[5][t]+" "+matrizAsientos[6][t]+" "+matrizAsientos[7][t]+" "+matrizAsientos[8][t]+" "+matrizAsientos[9][t]
            //               +" "+matrizAsientos[10][t]+" "+matrizAsientos[11][t]+" "+matrizAsientos[12][t]+" "+matrizAsientos[13][t]+" "+matrizAsientos[14][t]+" "+matrizAsientos[15][t]+" "+matrizAsientos[16][t]+" "+matrizAsientos[17][t]+" "+matrizAsientos[18][t]+" "+matrizAsientos[19][t]
              //             +" "+matrizAsientos[20][t]+" "+matrizAsientos[21][t]+" "+matrizAsientos[22][t]+" "+matrizAsientos[23][t]+" "+matrizAsientos[24][t]+" "+matrizAsientos[25][t]+" "+matrizAsientos[26][t]+" "+matrizAsientos[27][t]+" "+matrizAsientos[28][t]+" "+matrizAsientos[29][t]);
        //}
        
        Menu(nombres,apellidos,ruts,contraseñas,saldos,numClientes,peliculas,tipoPeliculas,recaudaciones,salas,funciones,numPeliculas);
    }
    
}
