package blockChainWallet;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransactionHistory extends JFrame {

	private JPanel contentPane;
	private JTable table,table1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionHistory frame = new TransactionHistory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TransactionHistory() {
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 11, 263, 170);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
					
				},
			new String[] {
				"Ng\u01B0\u1EDDi g\u1EEDi", "Ng\u01B0\u1EDDi nh\u1EADn", "S\u1ED1 ti\u1EC1n"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		btnTrV = new JButton("Trở về");
		btnTrV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBack();
			}
		});
		btnTrV.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnTrV.setBounds(227, 220, 182, 31);
		contentPane.add(btnTrV);
		
//		311, 11, 263, 170
		JScrollPane scrollPanePlayer = new JScrollPane();
		scrollPanePlayer.setBounds(311, 11, 263, 170);
		contentPane.add(scrollPanePlayer);
		
		table1 = new JTable();
		table1.setModel(new DefaultTableModel(
				new Object[][] {
					
				},
			new String[] {
				"Người", "Số tiền"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanePlayer.setViewportView(table1);

		init();
		
	}
	
	public void init() {		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		for (Block block : Main.blockchain) {
			for (Transaction trans : block.transactions) {
				String sender = StringUtil.getStringFromKey(trans.reciepient);
				sender = StringUtil.applySha256(sender);
				
				String sender1 = StringUtil.getStringFromKey(trans.sender);
				sender1 = StringUtil.applySha256(sender1);
				
				String nguoiGui;
				if (Main.accList.getAccountByPublicKey(sender1) == null) 
					nguoiGui = "HeThong";
				else
				nguoiGui = Main.accList.getAccountByPublicKey(sender1).getUserName();
				
				String nguoiNhan;
				if (Main.accList.getAccountByPublicKey(sender) == null)
					nguoiNhan = "HeThong";
				else
				nguoiNhan = Main.accList.getAccountByPublicKey(sender).getUserName();
				
				model.addRow(new Object[]{nguoiGui, nguoiNhan, trans.value});
				table.setModel(model);
			}
			
		}
		
//		============================================================================
		DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
		
		for (Account a : Main.accList.getListAcc()) {
    		model1.addRow(new Object[]{a.getUserName(), a.getWallet().getBalance()});
			table1.setModel(model1);
		}
		
	}
	
	private JButton btnTrV;
	
	public void btnBack() {
		this.setVisible(false);
		Home home = new Home();
		home.setVisible(true);
	}
}
