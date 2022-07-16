/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author ognje
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Connection con = null;
        
        
        
        try{
            String url ="jdbc:mysql://localhost:3306/iths",
                    user="root",
                    pass="";
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Uspostavljena veza izmedju baze i drajvermenedzera.");
            
            proveriStaJeUneo(con);
        } catch (ClassNotFoundException e){
            System.out.println("Nije ucitan drajver. "+ e);
        } catch (SQLException e){
            System.out.println("greska u povezivanju s bazon"+ e);
        }
    }
    public static void proveriStaJeUneo(Connection con) throws SQLException{
        Scanner scan = new Scanner(System.in);
        System.out.println("npr: 1 – izlistaj sve, 2 – unesi novog učenika, 3– izmeni , 4 – obriši učenika, 0 za kraj");
        int odgovor = scan.nextInt();
        switch(odgovor) {
            case 1:
              dugme1(con);
              break;
            case 2:
              dugme2(con);
              break;
              case 3:
              dugme3(con);
              break;
              case 4:
              dugme4(con);
              break;
              case 0:
              dugme0(con);
              break;
            default:
              System.out.println("nisi uneo dobar broj ili sta god //// ");
              proveriStaJeUneo(con);
          }
    }
    public static void dugme1(Connection con) throws SQLException{
        
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT br, ime, prezime, odeljenje FROM ucenik;");
        
        while (rs.next()) {
            System.out.format("\n[%d]: %s, %s, %s \n", rs.getInt("br"), rs.getString("ime"), rs.getString("prezime"), rs.getString("odeljenje") );
        }
        st.close();
        proveriStaJeUneo(con);

    }
    public static void dugme2(Connection con)throws SQLException{
            Scanner scan = new Scanner(System.in);

            Statement st = con.createStatement();
            System.out.println("\nIzabrao si da uneses, bolje bi ti bilo da uneses u dobrom formatu:");

            System.out.println("IME(STRING)");
            String ime = scan.nextLine();
            System.out.println("PREZIME(STRING)");
            String prezime = scan.nextLine();
            System.out.println("ODELJENJE(STRING)");
            String odeljenje = scan.nextLine();
          
            st.executeUpdate(String.format("INSERT INTO ucenik (ime, prezime, odeljenje) VALUES ('%s', '%s', '%s')", ime, prezime, odeljenje));
            
            st.close();
            proveriStaJeUneo(con);
    }
    public static void dugme3(Connection con)throws SQLException{
            Scanner scan = new Scanner(System.in);

            Statement st = con.createStatement();
            System.out.println("\nIzabrao si da alterujes, bolje bi ti bilo da uneses u dobrom formatu:");
            System.out.println("BR(INT) - id ucenika kojeg menjas");
            int br = Integer.parseInt(scan.nextLine());
            System.out.println("IME(STRING)");
            String ime = scan.nextLine();
            System.out.println("PREZIME(STRING)");
            String prezime = scan.nextLine();
            System.out.println("ODELJENJE(STRING)");
            String odeljenje = scan.nextLine();

          
            st.executeUpdate(String.format("UPDATE ucenik SET ime='%s', prezime='%s', odeljenje='%s' WHERE br='%d'", ime, prezime, odeljenje, br));
            
            st.close();
            proveriStaJeUneo(con);

    }
    public static void dugme4(Connection con)throws SQLException{
        
        Scanner scan = new Scanner(System.in);

            Statement st = con.createStatement();
            System.out.println("\nIzabrao si da obrises, bolje bi ti bilo da uneses u dobrom formatu:");
            System.out.println("BR(INT) - id ucenika koga brises");
            int br = Integer.parseInt(scan.nextLine());
            

          
            st.executeUpdate(String.format("DELETE FROM ucenik WHERE br='%d'", br));
            
            st.close();
            proveriStaJeUneo(con);
    }
    public static void dugme0(Connection con)throws SQLException{
            con.close();
            System.out.println("Zatvorena konekcija dovidjenja");
            
    }
    
}
