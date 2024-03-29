package floricultura.app.gui;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import floricultura.app.exception.NameNotFilledException;
import floricultura.app.model.Planta;

import floricultura.app.persistencia.FloriculturaDAO;

import javax.swing.JLabel;


public class TelaHome extends JFrame{
	
	private JMenuBar barraMenu;
	private JMenu menu;
	
	private JMenuItem mItemHome;
	private JMenuItem mItemSair;
	
	private JLabel lbNomePopular;
	private JLabel lbNomeCientífico;
	private JLabel lbDescricao;
	
	private JTextField txfNomePopular;
	private JTextField txfNomeCientifico;
	private JTextField txfDescricao;
	private JTable tabela;
	
	JScrollPane barraRolagem = new JScrollPane(tabela);
	
	public TelaHome() throws SQLException 
	{
		setTitle("Minhas Plantinhas");
		setSize(340, 240);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		criarMenu();
		exibirPlantas();

	}
	

	public void apagarCampos() {
		txfNomePopular.setText("");
		txfNomeCientifico.setText("");
		txfDescricao.setText("");
	}

	private void criarMenu() {
		// TODO Auto-generated method stub
		barraMenu = new JMenuBar();
		menu = new JMenu("Floricultura");
		mItemHome = new JMenuItem("Home");
		mItemSair = new JMenuItem("Sair");
		
		barraMenu.add(menu);
		
		
		menu.add(mItemHome);
		menu.add(mItemSair);
		mItemSair.addActionListener(new FecharAction());
		getContentPane().add(barraMenu, BorderLayout.NORTH);
	}
	private void criarLista()  {
		// TODO Auto-generated method stub
	}
	// --------------------------------------------------------------------------

		private class SalvarAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					FloriculturaDAO.getInstace().adicionar(new Planta(0, txfNomePopular.getText(), txfNomeCientifico.getText(), txfDescricao.getText()));
					JOptionPane.showMessageDialog(null, "Planta catalogada com sucesso");
					apagarCampos();
					
				} catch (NameNotFilledException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			
		}
		
		private class FecharAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		}
// --------------------------------------------------------------------------
		public void exibirPlantas(){
			tabela = new JTable();
			DefaultTableModel modelo = (DefaultTableModel)tabela.getModel();
	        modelo.setNumRows(0);
	        
	        FloriculturaDAO ldao = new FloriculturaDAO();
	        
	        try {
				for(Planta l:ldao.listarPlantas()) {
				    modelo.addRow(new Object[] {
				            l.getId(),
				            l.getNomePopular(),
				            l.getNomeCientifico(),
				            l.getDescricao(),
				         
				    });
				    
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        }
	 

		private Object Column(int i) {
			// TODO Auto-generated method stub
			return null;
		}
}

