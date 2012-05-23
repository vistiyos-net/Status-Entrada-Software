package interfaz.eventos;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.vistiyos.interfaz.Ventana;

import thread.Recovery;

public class recuperar implements ActionListener {
	
	private Ventana vnt;
	
	public recuperar(Ventana vnt){
		this.vnt=vnt;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Recovery(vnt).start();
	}

}
