import javax.swing.*;

import java.awt.*;

import java.util.*;

import java.awt.event.*;

public class Game implements ActionListener{

	HashMap<Integer, JButton> _Positions = new HashMap<Integer, JButton>();

	JFrame _Display = new JFrame();

	JLabel _Alert = new JLabel();

	JButton _Reset = new JButton("Reset");

	boolean _Turn = true;

	Game() {

		_Reset.setBounds(0, 0, 80, 20);

		_Reset.addActionListener(this);

		_Alert.setBounds(300, 80, 100, 10);

		_Display.add(_Reset);

		_Display.add(_Alert);

		for (int i = 0; i<9; i++) {
			_Positions.put(i, new JButton(" "));
		}

		for (int i = 0; i < 9; i ++) {
			_Positions.get(i).addActionListener(this);
			int x = i;
			int y = 0;
			while (x > 2) {
				x -= 3;
				y ++;
			}
			_Positions.get(i).setBounds(100+x*100, 100+y*100, 100, 100);
			_Display.add(_Positions.get(i));
		}


		_Display.setLayout(null);

		_Display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		_Display.setSize(500, 500);

		_Display.setTitle("Tic tac toe");

		_Display.setResizable(false);

		_Display.setVisible(true);

	}

	public boolean winner() {
		//We have to go throuhg each row and diagonal to determine if someone has won


		//First we check the verticles
		for (int i = 0; i < 3; i ++) {
			ArrayList<String> pos = new ArrayList<String>();
			for (int u = i; u < i + 7; u += 3) {
				pos.add(_Positions.get(u).getText());

			}

			if (pos.get(0) == pos.get(1) && pos.get(1) == pos.get(2)) {
				if (pos.get(0) != " "){
					return true;
				}
			}
		}
		for (int i = 0; i < 7; i += 3) {
			ArrayList<String> pos = new ArrayList<String>();
			for (int u = i; u < i + 3; u ++) {
				pos.add(_Positions.get(u).getText());
			}
			if (pos.get(0) == pos.get(1) && pos.get(1) == pos.get(2)) {
				if (pos.get(0) != " "){
					return true;
				}
			}
		}
		ArrayList<String> pos = new ArrayList<String>();
		for (int u = 0; u < 9; u += 4) {
			pos.add(_Positions.get(u).getText());
		}
		if (pos.get(0) == pos.get(1) && pos.get(1) == pos.get(2)) {
			if (pos.get(0) != " "){
					return true;
				}
		}

		pos = new ArrayList<String>();
		for (int u = 2; u < 7; u += 2) {
			pos.add(_Positions.get(u).getText());
		}
		if (pos.get(0) == pos.get(1) && pos.get(1) == pos.get(2)) {
			if (pos.get(0) != " "){
					return true;
				}
		}
		return false;
	}

	@Override
	public void actionPerformed (ActionEvent e) {
		for (int i = 0; i < _Positions.size(); i ++) {
			if (e.getSource() == _Positions.get(i)) {
				if (_Turn) {
					_Positions.get(i).setText("X");
					if (winner()) {
						_Alert.setText("Winner");
					}
					_Turn = false;
				} else if (!_Turn) {
					_Positions.get(i).setText("O");
					if (winner()) {
						_Alert.setText("Winner");

					}
					_Turn = true;
				}
			}
		} 
		if (e.getSource() == _Reset) {
			for (int i = 0; i < _Positions.size(); i ++) {
				_Positions.get(i).setText(" ");
			}
			_Turn = true;
			_Alert.setText("");
		}
	}

}

