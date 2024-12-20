package co.redcom.util.utilities;

import co.redcom.util.constants.Constants;

import java.util.List;

public class Validation {

    /**
     * Metodo generico que nos permite realizar validaciones
     * @param obj
     * @return booleano su cumple o no
     */
    public static boolean isNullOrEmpty(final Object obj) {
        boolean result = Boolean.FALSE;
        if (obj == null) {
            result = Boolean.TRUE;
        } else if (obj instanceof String) {
            String objString = (String) obj;
            if (objString.trim().equals(Constants.EMPTY)) {
                result = Boolean.TRUE;
            }
        } else if (obj instanceof String[]) {
            String[] objString = (String[]) obj;
            if (objString.length == 0) {
                result = Boolean.TRUE;
            }
        } else if (obj instanceof List<?>) {
            List<?> list = (List<?>) obj;
            if (list.isEmpty()) {
                result = Boolean.TRUE;
            }
        }
        return result;
    }

    /**
     * Método que valida si un objeto no es nulo.
     *
     * @param , con el objeto a validar.
     * @return boolean con el resultado de la operación.
     */
    public static boolean isNotNull(final Object obj) {
        return obj != null;
    }

}
