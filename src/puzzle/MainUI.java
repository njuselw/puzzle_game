package puzzle;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private JPanel contentPanel;
    private JPanel imagePanel;
    private JLabel timeLabel;
    private JMenuBar menuBar;
    private JMenu gameMenu = new JMenu("游戏");
    private JMenu settingsMenu = new JMenu("设置");
    private JMenuItem startItem = new JMenuItem("开始 ");
    private JMenuItem pauseItem = new JMenuItem("暂停");
    private JMenuItem stopItem = new JMenuItem("停止");
    private JMenuItem rankItem = new JMenuItem("玩家排行榜");
    private JMenuItem exitItem = new JMenuItem("退出");
    private JMenuItem gradeItem = new JMenuItem("难度设置");
    private JMenuItem picItem = new JMenuItem("魔板设置");
    private JMenuItem musicItem = new JMenuItem("背景音乐设置");
    
    private int grade = 3; //默认难度为3，即3*3式拼图
    private String picName;
    
    public MainUI() {
    	frame = new JFrame("拼图游戏");
    	contentPanel =new JPanel();
    	timeLabel = new JLabel("00:00:00");
    	menuBar = new JMenuBar();
    	gameMenu.add(startItem);
    	gameMenu.addSeparator();
    	pauseItem.setEnabled(false);
    	stopItem.setEnabled(false);
    	gameMenu.add(pauseItem);
    	gameMenu.add(stopItem);
    	gameMenu.addSeparator();
    	gameMenu.add(rankItem);
    	gameMenu.add(exitItem);
    	menuBar.add(gameMenu);
    	settingsMenu.add(gradeItem);
    	settingsMenu.add(picItem);
    	settingsMenu.add(musicItem);
    	menuBar.add(settingsMenu);
    	frame.setJMenuBar(menuBar);
    	frame.getContentPane().add(BorderLayout.SOUTH, contentPanel);
    	contentPanel.setBorder(new EmptyBorder(5,5,5,5));
        contentPanel.setLayout(null);
        contentPanel.add(timeLabel);
        
        frame.setLayout(null);
        frame.setBounds(100, 100, 770, 560);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void startGame() {
    	
    }
}