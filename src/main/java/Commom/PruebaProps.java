package Commom;
import java.util.Properties;

public class PruebaProps {

    String User;
    String Password;
    String Nombre;
    String Puesto;
    String LugarTrabajo;
    String Telefono;
    String Celular;
    String Correo;
    String ProveaDetalle;
    String InstitucionTrabaja;
    String TituloInvestigacion;
    String Objetivo;
    String ResumenMetodologia;
    String Muestra;
    String ImportanciaUtilidad;

    public PruebaProps(Properties props){
        this.User =  props.getProperty("User");
        this.Password = props.getProperty("Password");
        this.Nombre =  props.getProperty("Nombre");
        this.Puesto =  props.getProperty("Puesto");
        this.LugarTrabajo = props.getProperty("LugarTrabajo");
        this.Telefono =  props.getProperty("Telefono");
        this.Celular = props.getProperty("Celular");
        this.Correo = props.getProperty("Correo");
        this.ProveaDetalle = props.getProperty("ProveaDetalle");
        this.InstitucionTrabaja = props.getProperty("InstitucionTrabaja");
        this.TituloInvestigacion = props.getProperty("TituloInvestigacion");
        this.Objetivo = props.getProperty("Objetivo");
        this.ResumenMetodologia = props.getProperty("ResumenMetodologia");
        this.Muestra = props.getProperty("Muestra");
        this.ImportanciaUtilidad = props.getProperty("ImportanciaUtilidad");

    }

    public String getUser(){
        return User;
    }

    public String getPassword(){
        return Password;
    }

    public String getNombre(){
        return Nombre;
    }

    public String getPuesto(){
        return Puesto;
    }

    public String getLugarTrabajo(){
        return LugarTrabajo;
    }

    public String getTelefono(){
        return Telefono;
    }

    public String getCelular(){
        return Celular;
    }

    public String getCorreo(){
        return Correo;
    }

    public String getProveaDetalle(){
        return ProveaDetalle;
    }

    public String getInstitucionTrabaja(){
        return InstitucionTrabaja;
    }

    public String getTituloInvestigacion(){
        return TituloInvestigacion;
    }

    public String getObjetivo(){ return Objetivo; }

    public String getResumenMetodologia(){
        return ResumenMetodologia;
    }

    public String getMuestra(){
        return Muestra;
    }

    public String getImportanciaUtilidad(){
        return ImportanciaUtilidad;
    }
}
