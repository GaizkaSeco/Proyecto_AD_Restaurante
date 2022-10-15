import java.io.Serializable;

public class Empleado implements Serializable {
    int id;
    String nombre;
    double salario;
    String fechaCon;
    int telefono;
    String email;

    public Empleado(int id, String nombre, Double salario, String fechaCon, int telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
        this.fechaCon = fechaCon;
        this.telefono = telefono;
        this.email = email;
    }

    public Empleado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getFechaCon() {
        return fechaCon;
    }

    public void setFechaCon(String fechaCon) {
        this.fechaCon = fechaCon;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
