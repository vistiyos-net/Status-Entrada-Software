package interfaz.eventos;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.vistiyos.inferfaz.Password;
import net.vistiyos.inferfaz.Ventana;

public class mostrarTeclado implements ActionListener {
	
	private Ventana vnt;
	private Password teclado;
	
	public mostrarTeclado(Ventana vnt){
		this.vnt=vnt;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.teclado=new Password(this.vnt);
		this.teclado.mostrar();
	}

}