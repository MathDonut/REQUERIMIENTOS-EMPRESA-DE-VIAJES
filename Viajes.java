
package viajes;
import java.util.Scanner;  //Libreria del teclado
import java.io.BufferedWriter; //Librerias para la creacion del archivo txt
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;//Agrego las librerias de fecha y hora para que queden registradas en el txt
import java.time.LocalTime;

public class Viajes {
public static void main(String[] args) {
    
 Scanner teclado = new Scanner (System.in);
 
 // Valor estandar de las maletas 
 
 double maletabasica = 285325; 
 double maletapremium = 325650;
 
 // Variables para guardar opciones del usuario
 int acomp = 0;
 String tipomaleta ="";
 double costomaleta = 0;
 double valortotal = 0;
 
 
 //CREO BANDERAS DECLARATIVAS EN CASO DE QUE EL USUARIO SE SALTE PASOS ASI NEGANDOLE LA OPCION A CREAR LA FACTURA Y EL VALOR DEL VIAJE (OPCION 4 Y 5)
 boolean maletalista = false;
 boolean acomplistos = false;
 
 
 int opcion=0;
 String nombre="", numdoc="", fechaV="", destino="";
 
 //CREO LAS VARIABLES PARA PONER LA HORA Y FECHA EN LA QUE SE CREA EL ARCHIVO TXT
 
 LocalDate fecha = LocalDate.now();
 LocalTime hora = LocalTime.now();
 
 

     
 
     System.out.println ("BIENVENIDOS A CORTES TOURS");
     
     //Creo el menu usando un ciclo repetitivo WHILE
 
 while (opcion!=7){
     
     System.out.println("***MENU DE REGISTRO***");
     
     System.out.println("1. Registrar cliente (Nombre, cedula, fecha de viaje y destino)");
     
     System.out.println("2. Tipo de maleta");
     
     System.out.println("3. Acompañantes");
     
     System.out.println("4. Valor del viaje");
     
     System.out.println("5. Visualizar toda la informacion");
     
     System.out.println("6. Respaldo txt");
     
     System.out.println("7. Salir");
     
     
     System.out.println ("USUARIO INGRESE UNA OPCION");
     opcion = teclado.nextInt();
     teclado.nextLine();
     //CREO UN SWITCH CASE VARIAS VECES PARA LAS OPCIONES DEL USUARIO CERRANDO CADA UNO CON UN BREAK;
     
     
    switch (opcion){
        
        
        case 1:
            System.out.println ("INGRESE SU NOMBRE COMPLETO O ESCRIBA (vacaciones) PARA TERMINAR");
            nombre = teclado.nextLine();
            //Uso este if para darle uso a la palabra clave "vacaciones" que me pidio el cliente para cerrar el proceso
            if (nombre.equalsIgnoreCase("vacaciones")){
                System.out.println("AVISO!!! se ha introducido la palabra clave Finalizando factura actual...");
                break;
            }
            
            System.out.println("INGRESE SU NUMERO DE DOCUMENTO");
            numdoc = teclado.nextLine();
            
            System.out.println("INGRESE LA FECHA DE SU VIAJE");
            fechaV = teclado.nextLine();
            
            System.out.println("INGRESE SU DESTINO");
            destino = teclado.nextLine();
            break;
            
        case 2:
            System.out.println("Escriba MB para seleccionar la maleta Basica($285.325 COP)");
            System.out.println ("Escriba MP para seleccionar la maleta Premium ($325.650 COP)");
            tipomaleta = teclado.nextLine();
            
            //CREO UN IF PARA REGISTRAR LOS DOS TIPOS DE MALETA Y CONVERTIRLOS AL VALOR A OPERAR EN EL CASO 4
            if (tipomaleta.equalsIgnoreCase("MB")) {
            costomaleta = maletabasica;
            maletalista = true;
            System.out.println("!Maleta Basica seleccionada con exito!");
            }else if (tipomaleta.equalsIgnoreCase("MP")) {
            costomaleta = maletapremium;
            maletalista = true;
            System.out.println("!Maleta Premium seleccionada con exito!");
            } else {
            System.out.println("Siglas no validas. Debe escribir MB o MP.");
            costomaleta = 0;
            }
            break;
    
        //Dejo que el usuario ingrese el numero de acompañantes 
        case 3:
            System.out.println("INGRESE EL NUMERO DE ACOMPAÑANTES");
            acomp= teclado.nextInt();
            acomplistos = true;
            break;
            
        case 4: 
            //Hago la operacion para crear el valor total del viaje y la concateno en lo que el sistema va a decir
            //tambien agrego mis dos banderas para soltar error y obligar al usuario a completar los otros dos campos
            if (!maletalista|| !acomplistos){
                System.out.println ("ERROR!!! faltan pasos previos. Verifique que selecciono el tipo de maleta y los acompañantes.");
                }else{
                int totalpersonas = acomp + 1; //Calculo la operacion sumandole 1 para mejorar el resultado del codigo en un uso de la vida real en caso de que el usuario quiera 0 acompañantes
            valortotal = (costomaleta) * (totalpersonas);
            System.out.println("EL VALOR DEL VIAJE ES= " + valortotal + "COP");
            }
            break;
            
        case 5: 
            System.out.println("================================");
            System.out.println("LA INFORMACION REGISTRADA ES: ");
            System.out.println("Nombre ingresado: " + nombre);
            System.out.println("Numero de documento:  " + numdoc);
            System.out.println("Fecha de viaje:  " + fechaV);
            System.out.println("Destino: " + destino);
            
            if (tipomaleta.equalsIgnoreCase("MB")){
                System.out.println("Tipo de maleta: Maleta Basica (MB)");
                }else if (tipomaleta.equalsIgnoreCase ("MP")){
                    System.out.println ("Tipo de maleta: Maleta Premium (MP)");
                }
            System.out.println ("Numero de acompañantes:  " + acomp);
            System.out.println("El valor total es:  " + valortotal);
            System.out.println("================================");;
            break;
            
        case 6: 
            // Valido primero que si se hayan ingresado los acompañantes y el tipo de maleta para no crear un txt a medias o vacio
            if (!maletalista || !acomplistos) {
            System.out.println("ERROR!!! Debe completar el registro y calcular el valor antes de generar el respaldo.");
            } else {
            
            //Utilizo un try y un catch para la creacion del archivo txt del usuario
            try{
                //Creo las variables fw y bw para escribir adentro de el respaldo txt
                FileWriter fw = new FileWriter ("C:\\\\Users\\\\Janus\\\\OneDrive\\\\Desktop\\\\PruebaViajes.txt");
                BufferedWriter bw = new BufferedWriter (fw);
                //Creo una nueva variable para que el tipo de maleta seleccionado se escriba completo en el txt en lugar de MB o MP
                String descMaleta = "";
                //Uso un if y un else condicionales para que lea lo escrito en la opcion 2 y asi mismo el sistema escriba el tipo de maleta automaticamente
                if (tipomaleta.equalsIgnoreCase("MB")) {
                descMaleta = "Maleta Básica (MB)";
                } else {
                descMaleta = "Maleta Premium (MP)";
                }
                
                bw.write("=============DATOS REGISTRADOS=============\n");
                bw.write("Fecha del registro:   " + fecha + "\n");
                bw.write("Hora del registro:  " + hora + "\n");
                bw.write("Nombre ingresado:  " + nombre + "\n");
                bw.write("Numero de documento ingresado:  "  + numdoc + "\n");
                bw.write("Fecha del viaje ingresada:  " + fechaV + "\n");
                bw.write("Lugar de destino ingresado:  " + destino + "\n");
                bw.write("Tipo de Maleta seleccionada:   "  + descMaleta + "\n");
                bw.write("Numero de acompañantes registrados:   " + acomp  + "\n");
                bw.write("Valor total del viaje:  "  + valortotal + " COP\n");
                bw.write("==========================================");
                
                bw.close();
                fw.close();
                
                System.out.println("El respaldo txt fue creado y guardado con exito en el dispositivo!");
                
                } catch (IOException error){
                    System.out.println("ALERTA VERIFICAR RESPALDO"  + error.getMessage());
                    teclado.close();
                }  
                }
            break;
            
            case 7:
                  System.out.println("Saliendo del sistema de Cortes Tours. Vuelva Pronto!");
                  break;
                  //Dejo el break final para que la consola deje de compilar
            default: System.out.println("Opcion invalida, Por favor, Escriba un numero del 1 al 7.");
            
            
 
     
            }
            }
 teclado.close(); //Le doy cierre al sistema
}
}
