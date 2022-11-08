package xml.modelos;

public class Empleado {

    private int id, dpt;
    private String apellido;
    private float salario;

    public Empleado(int id, int dpt, String apellido, float salario) {
        this.id = id;
        this.dpt = dpt;
        this.apellido = apellido;
        this.salario = salario;
    }

    public Empleado() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDpt() {
        return dpt;
    }

    public void setDpt(int dpt) {
        this.dpt = dpt;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", dpt=" + dpt +
                ", apellido='" + apellido + '\'' +
                ", salario=" + salario +
                '}';
    }
}
