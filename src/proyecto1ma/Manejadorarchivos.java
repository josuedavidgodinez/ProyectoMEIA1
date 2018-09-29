/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1ma;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jdk.jfr.events.FileWriteEvent;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author godin
 */
public class Manejadorarchivos {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private boolean verificarsiexiste(String usuario) throws IOException {
        String cadena;
        File f=new File("C:\\MEIA\\usuarios.txt");
         String[] datos;
        
        if(f.exists()){
         FileReader fr = new FileReader(f);
        
        BufferedReader b = new BufferedReader(fr);
       
        while ((cadena = b.readLine()) != null) {

            datos = cadena.split("/|");
            if (datos[0].equals(usuario)) {
                return true;
            }

        }
         b.close();
        fr.close();
        }
        
        FileReader f2 = new FileReader("C:\\MEIA\\bitacora_usuarios.txt");
        BufferedReader b2 = new BufferedReader(f2);
        String[] datos2;
        while ((cadena = b2.readLine()) != null) {

            datos = cadena.split("\\|");
            if (datos[0].equals(usuario)) {
                return true;
            }

        }
         b2.close();
        f2.close();
       
       
        
        return false;
    }

    public int contarregistros(String path) throws FileNotFoundException, IOException {
        int contador = 0;
        String cadena;
        FileReader f = new FileReader(path);
        BufferedReader b = new BufferedReader(f);

        while ((cadena = b.readLine()) != null) {

            contador++;

        }
        b.close();
        f.close();
        
        return contador;
    }

    public int contarregistrosactivos(String path) throws FileNotFoundException, IOException {
        int contador = 0;
        String cadena;
        FileReader f = new FileReader(path);
        BufferedReader b = new BufferedReader(f);
        String datos[];
        while ((cadena = b.readLine()) != null) {
            datos = cadena.split("\\|");

            if (1 == Integer.valueOf(datos[9])) {
                contador++;

            }
        }
         b.close();
        f.close();
       
        return contador;
    }

    public boolean verificarsibitacoraestallena() throws IOException {
        int contador = 0;
        String cadena;
        File descritorbitacora;

        descritorbitacora = new File("C:\\MEIA\\desc_bitacora_usuarios.txt");
        if (!descritorbitacora.exists()) {
            descritorbitacora.createNewFile();
        }
        FileReader f = new FileReader(descritorbitacora);
        BufferedReader b = new BufferedReader(f);
        String[] datos = new String[2];

        while ((cadena = b.readLine()) != null) {

            if (contador == 8) {
                datos = cadena.split(":");
            }
            contador++;

        }
        b.close();
        f.close();

        if (contarregistros("C:\\MEIA\\bitacora_usuarios.txt") == Integer.valueOf(datos[1])) {
            return true;
        } else {
            return false;
        }
    }

    public void transferirdatosamaster(usuario modificador) throws FileNotFoundException, IOException {

        String cadena;
        File bitacora = new File("C:\\MEIA\\bitacora_usuarios.txt");
        File master = new File("C:\\MEIA\\usuarios.txt");
        FileReader fr = new FileReader(bitacora.getAbsoluteFile());
        
        
        FileWriter fw = new FileWriter(master.getAbsoluteFile(), true);
        BufferedReader br = new BufferedReader(fr);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter wr = new PrintWriter(bw);
        if (!master.exists()) {
            master.createNewFile();

        }
        while ((cadena = br.readLine()) != null) {

            wr.println(cadena);

        }
        br.close();
        bw.close();
        fr.close();
        fw.close();
        
        FileWriter fwb=new FileWriter(bitacora,false);
      fwb.write("");
        fwb.close();
        
        actualizardescriptores(modificador);
        

    }

    public void ordenarmaster() throws FileNotFoundException {

    }

    public void actualizardescriptores(usuario modificador) throws IOException {

        File descriptorusuarios;
        File descritorbitacora;

        descriptorusuarios = new File("C:\\MEIA\\desc_usuarios.txt");
        descritorbitacora = new File("C:\\MEIA\\desc_bitacora_usuarios.txt");

        if (verificarsibitacoraestallena()) {
            if (!descriptorusuarios.exists()) {
                descriptorusuarios.createNewFile();
                FileWriter filewriterdescriptorusuarios = new FileWriter(descriptorusuarios.getAbsoluteFile(), true);
                BufferedWriter bufferwriterdescriptorusuarios = new BufferedWriter(filewriterdescriptorusuarios);
                PrintWriter printdescriptorusuarios = new PrintWriter(bufferwriterdescriptorusuarios);
                printdescriptorusuarios.println("nombre_simbolico:usuarios");
                printdescriptorusuarios.println("fecha_creacion:" + new Date());
                printdescriptorusuarios.println("usuario_creacion:" + modificador.getUsuario());
                printdescriptorusuarios.println("fecha_modificacion:" + new Date());
                printdescriptorusuarios.println("usuario_modificacion:" + modificador.getUsuario());
                printdescriptorusuarios.println("#_registros:" + contarregistros("C:\\MEIA\\desc_usuarios.txt"));
                printdescriptorusuarios.println("registros_activos:" + contarregistrosactivos("C:\\MEIA\\desc_usuarios.txt"));
                printdescriptorusuarios.println("registros_inactivos:" + (contarregistros("C:\\MEIA\\desc_usuarios.txt") - contarregistrosactivos("C:\\MEIA\\desc_usuarios.txt")));
                printdescriptorusuarios.println("max_reorganizacion:3");
                 printdescriptorusuarios.close();
                  bufferwriterdescriptorusuarios.close();
                filewriterdescriptorusuarios.close();
                
               
              
            } else {
                FileWriter filewriterdescriptorusuarios = new FileWriter(descriptorusuarios.getAbsoluteFile(), true);
                BufferedWriter bufferwriterdescriptorusuarios = new BufferedWriter(filewriterdescriptorusuarios);
                PrintWriter printdescriptorusuarios = new PrintWriter(bufferwriterdescriptorusuarios);
                printdescriptorusuarios.print(modificardescriptorcreado("C:\\MEIA\\desc_usuarios.txt", modificador,"C:\\MEIA\\usuarios.txt"));

                printdescriptorusuarios.close();
                bufferwriterdescriptorusuarios.close();
                filewriterdescriptorusuarios.close();

            }

        }

        if (!descritorbitacora.exists()) {
            iniciardesbitacorausuario(modificador);
        } else {
            FileWriter filewriterdescriptorbitacora = new FileWriter(descritorbitacora.getAbsoluteFile(), true);
            BufferedWriter bufferwriterdescriptorbitacora = new BufferedWriter(filewriterdescriptorbitacora);
            PrintWriter printdescriptorbitacora = new PrintWriter(bufferwriterdescriptorbitacora);
            filewriterdescriptorbitacora.write(modificardescriptorcreado("C:\\MEIA\\desc_bitacora_usuarios.txt", modificador, "C:\\MEIA\\bitacora_usuarios.txt"));
            printdescriptorbitacora.close();
            bufferwriterdescriptorbitacora.close();

            filewriterdescriptorbitacora.close();

        }

    }

    public void iniciardesbitacorausuario(usuario modificador) throws IOException {
        File descritorbitacora;

        descritorbitacora = new File("C:\\MEIA\\desc_bitacora_usuarios.txt");
        descritorbitacora.createNewFile();
        FileWriter filewriterdescriptorbitacora = new FileWriter(descritorbitacora, false);
        BufferedWriter bufferwriterdescriptorbitacora = new BufferedWriter(filewriterdescriptorbitacora);
        PrintWriter printdescriptorbitacora = new PrintWriter(bufferwriterdescriptorbitacora);
        printdescriptorbitacora.println("nombre_simbolico:bitacora_usuarios");
        printdescriptorbitacora.println("fecha_creacion:" + new Date());
        printdescriptorbitacora.println("usuario_creacion:" + modificador.getUsuario());
        printdescriptorbitacora.println("fecha_modificacion:" + new Date());
        printdescriptorbitacora.println("usuario_modificacion:" + modificador.getUsuario());
        printdescriptorbitacora.println("#_registros:" + contarregistros("C:\\MEIA\\desc_bitacora_usuarios.txt"));
        printdescriptorbitacora.println("registros_activos:" + contarregistrosactivos("C:\\MEIA\\desc_bitacora_usuarios.txt"));
        printdescriptorbitacora.println("registros_inactivos:" + (contarregistros("C:\\MEIA\\desc_bitacora_usuarios.txt") - contarregistrosactivos("C:\\MEIA\\desc_bitacora_usuarios.txt")));
        printdescriptorbitacora.println("max_reorganizacion:3");

        printdescriptorbitacora.close();
        bufferwriterdescriptorbitacora.close();
        filewriterdescriptorbitacora.close();

    }

    public String modificardescriptorcreado(String pathdescriptor, usuario user,String pathprincipal) throws FileNotFoundException, IOException {
        
        byte[] encoded = Files.readAllBytes(Paths.get(pathdescriptor));
        File f=new File(pathdescriptor);
        FileWriter fw=new FileWriter(f,false);
        fw.write("");
        fw.close();
        String salida = "";
        String textoentero = new String(encoded);
        String eol = System.getProperty("line.separator");
        String registros[] = textoentero.split("\\r?\\n");
        salida += registros[0] + eol;
        salida += registros[1] + eol;
        salida += registros[2] + eol;
        salida += "fecha_modificacion:" + new Date() + eol;
        salida += "usuario_modificacion:" + user.getUsuario() + eol;
        salida += ("#_registros:" + contarregistros(pathprincipal) + eol);
        salida += ("registros_activos:" + contarregistrosactivos(pathprincipal) + eol);
        salida += ("registros_inactivos:" + (contarregistros(pathprincipal) - contarregistrosactivos(pathprincipal)) + eol);
        salida += ("max_reorganizacion:3");
        return salida;
    }

    public usuario registrar(String user, String name, String Lastname, String pass, Date date, String mail, String tel, String pathfoto, usuario modificador) throws IOException {

        File bitacora;
         File descbitacora=new File("C:\\MEIA\\desc_bitacora_usuarios.txt");
        String isadmin = "0";

        usuario usuarioacrear = new usuario();

        bitacora = new File("C:\\MEIA\\bitacora_usuarios.txt");
    
        File carpetafotos = new File("C:\\MEIA\\fotografia");

        try {
            if (!bitacora.exists()) {
                bitacora.createNewFile();
               
                isadmin = "1";
                usuarioacrear.setRol(true);

            } else {
                usuarioacrear.setRol(false);
            }
            if (!carpetafotos.exists()) {
                carpetafotos.mkdirs();
            }

            if (!verificarsiexiste(user)) {
                File outputfile = new File("C:\\MEIA\\fotografia\\" + user + ".jpg");
                File getfile = new File(pathfoto);

                FileWriter w = new FileWriter(bitacora.getAbsoluteFile(), true);

                BufferedWriter bw = new BufferedWriter(w);

                PrintWriter wr = new PrintWriter(bw);
                FileUtils.copyFile(getfile, outputfile);
                usuarioacrear.setUsuario(user);
                usuarioacrear.setNombre(name);
                usuarioacrear.setApellido(Lastname);
                usuarioacrear.setPassword(pass);
                usuarioacrear.setFecha_nacimiento(date);
                usuarioacrear.setCorreo_alternativo(mail);
                usuarioacrear.setPath_fotografia(outputfile.getAbsolutePath());
                usuarioacrear.setTelefono(Integer.valueOf(tel));
                usuarioacrear.setStatus(true);

                wr.println(user + "|" + name + "|" + Lastname + "|" + pass + "|" + isadmin + "|" + formatter.format(date.getDate()) + "|" + mail + "|" + tel + "|" + outputfile.getAbsolutePath() + "|1");

                wr.close();

                bw.close();
                
                 if (!descbitacora.exists()) {
                 if (modificador.isRol()) {
                    iniciardesbitacorausuario(modificador);
                } else {
                    iniciardesbitacorausuario(usuarioacrear);
                }
                 }
               
               
                if (modificador.isRol()) {
                    actualizardescriptores(modificador);
                } else {
                    actualizardescriptores(usuarioacrear);
                }

                if (verificarsibitacoraestallena()) {
                    if (modificador.isRol()) {
                        transferirdatosamaster(modificador);
                    } else {
                        transferirdatosamaster(usuarioacrear);
                    }
                }

                return usuarioacrear;

            } else {
                JOptionPane.showMessageDialog(null, "El ususrio ingresado ya existe");
                return null;
            }
        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, e);
            return null;

        }

    }

}
