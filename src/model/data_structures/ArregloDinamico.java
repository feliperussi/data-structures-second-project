package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico implements IArregloDinamico {
		/**
		 * Capacidad maxima del arreglo
		 */
        private int tamanoMax;
		/**
		 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
		 */
        private int tamanoAct;
        /**
         * Arreglo de elementos de tamaNo maximo
         */
        private String elementos[ ];

        /**
         * Construir un arreglo con la capacidad maxima inicial.
         * @param max Capacidad maxima inicial
         */
		public ArregloDinamico( int max )
        {
               elementos = new String[max];
               tamanoMax = max;
               tamanoAct = 0;
        }
        
		public void agregar( String dato )
        {
               if ( tamanoAct == tamanoMax )
               {  // caso de arreglo lleno (aumentar tamaNo)
                    tamanoMax = 2 * tamanoMax;
                    String [ ] copia = elementos;
                    elementos = new String[tamanoMax];
                    for ( int i = 0; i < tamanoAct; i++)
                    {
                     	 elementos[i] = copia[i];
                    } 
            	    System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
               }	
               elementos[tamanoAct] = dato;
               tamanoAct++;
       }

		public int darCapacidad() {
			return tamanoMax;
		}

		public int darTamano() {
			return tamanoAct;
		}

		public String darElemento(int i) {
			// Implementar
			if (i< tamanoAct) {
				return elementos[i];
			}
			else {
				//System.out.println("No hay un elemento en la posicion: "+ i);
				return null;
			}
		}
	
		public String buscar(String dato) {
			// Implementar
			// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
			boolean termino=false;
			int i=0;
			String resp= null;
			while (termino==false) {
				//Revisa que aun estoy en el arreglo y no he encontrado el dato
				if(i<tamanoAct) {
				int prueba = dato.compareTo(elementos[i]);
				if(prueba==0) {
					resp=elementos[i]; 
					termino=true;
				}
					i++;
				}
				else{
					termino=true;
					//System.out.println("El dato no se encuentra en el arreglo");
				}
			}
			return resp;
		}

		public String eliminar(String dato) {
			// Implementar
			// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
			boolean termino=false;
			int i=0;
			String resp= null;
			while (termino==false) {
				//Busco el dato y su posicion
				if(i<tamanoAct) {
				int prueba = dato.compareTo(elementos[i]);
				if(prueba==0) {
					resp=elementos[i];
					//Modifica el arreglo para que sea compacto
					String [ ] copia = elementos;
					elementos = new String[tamanoMax];
					for (int j=0; j < tamanoAct; j++) { //Elementos antes quedan igual
						if(j<i) {
							elementos[j]=copia[j];
						}
						if(j>i) {
							elementos[j-1]=copia[j];
						}			
					}
					tamanoAct--;
					termino=true;
				}
					i++;
				}
				else{
					termino=true;
				}
			}
			return resp;
		}

}
