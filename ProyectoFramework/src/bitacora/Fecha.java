package bitacora;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Fecha {

    public Fecha() {
    }

    public String Fecha(Date date) {
        try {
            Archivo.escribirBitacora(this.getClass(), new Object() {
            }.getClass().getEnclosingMethod().getName());
        } catch (IOException ex) {
        }

        return LocalDate.now() + "," + LocalTime.now();

    }

    @Override
    public String toString() {
        return LocalDate.now() + "," + LocalTime.now();
    }

}
