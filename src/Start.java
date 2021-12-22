import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

public class Start {
    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet list = null;
        Scanner scanner = new Scanner(System.in);
        int seleccion = 1;
        int identificador;
        String nombre = null;
        float precio = 0.0f;
        int cantidad = 0;
        int status;

        try {
            // Creo una conexion
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "");
            // Creo el objeto statement
            statement = (Statement) connection.createStatement();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

            //statement.executeUpdate("INSERT INTO `productos`(`id`, `nombre`, `precio`, `cantidad`) VALUES (NULL,'zapatos',50,1)");

            while (seleccion != 0) {
                System.out.println("Gestor de Productos");
                System.out.println("    1)Crear un producto");
                System.out.println("    2)Listar todos los productos");
                System.out.println("    3)Modificar un producto");
                System.out.println("    4)Borrar un producto");
                System.out.println("    0)Salir del programa");
                System.out.print("Opcion ( 0-4 ):");
                seleccion = scanner.nextInt();

                switch (seleccion) {
                    case 1:
                            System.out.print("Introduzca el nombre del producto: ");
                            nombre = scanner.next();
                            System.out.print("Introduzca el precio del producto: ");
                            precio = scanner.nextFloat();
                            System.out.print("Intoduzca la cantidad del producto: ");
                            cantidad = scanner.nextInt();
                            status = statement.executeUpdate("INSERT INTO `productos`(`nombre`, `precio`,`cantidad`)VALUES('"+nombre+"',"+precio+","+cantidad+")");
                            if(status==1){
                                System.out.println("Producto creado correctamente");
                                break;
                            }
                            else {
                                System.out.println("El producto no se ha podido crear");
                                break;
                            }
                    case 2:
                            list = statement.executeQuery("Select * from productos");
                            while (list.next()){
                                System.out.println(list.getInt("id") + " - " + list.getString("nombre") + " - " + list.getFloat("precio") + "€ - " + list.getInt("cantidad") + " unidades");
                            }
                        break;
                    case 3:
                            list = statement.executeQuery("Select * from productos");
                            while (list.next()){
                                System.out.println(list.getInt("id") + " - " + list.getString("nombre") + " - " + list.getFloat("precio") + "€ - " + list.getInt("cantidad"));
                            }
                            System.out.print("Introduzca el identificador del producto: ");
                            identificador = scanner.nextInt();
                            System.out.print("Introduzca el nombre del producto: ");
                            nombre = scanner.next();
                            System.out.print("Introduzca el precio del producto: ");
                            precio = scanner.nextFloat();
                            System.out.print("Intoduzca la cantidad del producto: ");
                            cantidad = scanner.nextInt();
                            status = statement.executeUpdate("UPDATE `productos` SET `nombre`='"+nombre+"',`precio`="+precio+",`cantidad`="+cantidad+" WHERE `id`="+identificador+"");
                            if (status == 1){
                                System.out.println("Producto modificado correctamente");
                                break;
                            }
                            else{
                                System.out.println("El producto no se ha podido modificar");
                                break;
                            }
                    case 4:
                        System.out.print("Introduzca el identificador del producto a eliminar: ");
                        identificador=scanner.nextInt();
                            status = statement.executeUpdate("DELETE FROM `productos` WHERE `id`="+identificador+"");
                            if (status == 1){
                                System.out.println("Producto eliminado correctamente");
                                break;
                            }
                            else {
                                System.out.println("El producto no se ha eliminado");
                                break;
                            }
                    case 0:
                        seleccion = 0;
                        System.out.println("Chao");
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                        break;
                }
            }
    }
}
