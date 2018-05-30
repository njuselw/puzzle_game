package puzzle;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
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
    private JMenuItem startItem = new JMenuItem("开始");
    private JMenuItem pauseItem = new JMenuItem("暂停");
    private JMenuItem stopItem = new JMenuItem("停止");
    private JMenuItem rankItem = new JMenuItem("玩家排行榜");
    private JMenuItem exitItem = new JMenuItem("退出");
    private JMenuItem gradeItem = new JMenuItem("难度设置");
    private JMenuItem picItem = new JMenuItem("魔板设置开");
    private JMenuItem nextPicItem = new JMenuItem("下一张背景图");
    private JMenuItem musicItem = new JMenuItem("背景音乐开");
    private JMenuItem nextMusicItem = new JMenuItem("下一首背景音乐");
    
    boolean isPic = false;//是否有背景图片
    int isPause = 0;//是否暂停    
    private int grade = 3; //默认难度为3，即3*3式拼图
    private String[] picNameList = {"1", "2", "3"};
    private String[] musicList = {"1", "2", "3"};
    private int picIndex = 0;
    private int musicIndex = 0;
    private int time = 0;
    private int blankRow = 2;
    private int blankCol = 2;
    private ImageButton[][] btnField = null;
    private int[][] numField;
    
    private Timer timer;
    private AudioClip a;
    
    public MainUI() {
    	contentPanel = new JPanel();
    	contentPanel.setBorder(new EmptyBorder(5,5,5,5));
        contentPanel.setLayout(null);
        
        timeLabel = new JLabel("");
        timeLabel.setFont(new Font("Dialog", 1, 15));
    	timeLabel.setBounds(10, 10, 495, 50);
    	
    	imagePanel = new JPanel();
    	
        imagePanel.setBounds(10, 60, 450, 450);
        contentPanel.add(imagePanel);
        
    	contentPanel.add(timeLabel);
    	
    	startItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
    	pauseItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
    	stopItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
    	rankItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
    	exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
    	
    	startItem.addActionListener(new MenuItemActionListener());
    	pauseItem.addActionListener(new MenuItemActionListener());
    	stopItem.addActionListener(new MenuItemActionListener());
    	rankItem.addActionListener(new MenuItemActionListener());
    	exitItem.addActionListener(new MenuItemActionListener());
    	gradeItem.addActionListener(new MenuItemActionListener());
    	picItem.addActionListener(new MenuItemActionListener());
    	nextPicItem.addActionListener(new MenuItemActionListener());
    	musicItem.addActionListener(new MenuItemActionListener());
    	nextMusicItem.addActionListener(new MenuItemActionListener());
    	
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
    	nextPicItem.setEnabled(false);
    	nextMusicItem.setEnabled(false);
    	settingsMenu.add(gradeItem);
    	settingsMenu.addSeparator();
    	settingsMenu.add(picItem);
    	settingsMenu.add(nextPicItem);
    	settingsMenu.addSeparator();
    	settingsMenu.add(musicItem);
    	settingsMenu.add(nextMusicItem);
    	menuBar.add(settingsMenu);
    	
    	
    	this.setJMenuBar(menuBar);
    	this.setContentPane(contentPanel);    
    	this.setTitle("魔板游戏");
        
    	this.setLayout(null);
        this.setBounds(100, 100, 495, 595);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void startGame() {
    	startItem.setEnabled(false);
    	pauseItem.setEnabled(true);
    	stopItem.setEnabled(true);
    	timeLabel.setText(getStringTime());
    	imagePanel.setBackground(Color.ORANGE);
    	
    	time = 0;
    	
    	init();
    	
    	ImageCtrl ic = new ImageCtrl(picNameList[picIndex]);
    	ic.deletePic();
    	ic.cutPic(grade, grade, grade);
    	
    	for (int i = 0; i < grade; i++) {
    		for (int j = 0; j < grade; j++) {
    			ImageButton ib = new ImageButton(i, j, 450 / grade, numField[i][j], picNameList[picIndex], isPic);
    			imagePanel.add(ib);
    			btnField[i][j] = ib;
    			ib.addActionListener(new ImageButtonActionListener());
    		}
    	}
    	
    	blankRow = grade - 1;
    	blankCol = grade - 1;
    	btnField[blankRow][blankCol].setImage(false, isPic);
    	
    }
    
    public void init() {
    	Random random = new Random();
    	
    	btnField = new ImageButton[grade][grade];
    	imagePanel.removeAll();
    	imagePanel.setLayout(new GridLayout(grade, grade));
    	
    	numField =  new int[grade][grade];
    	for (int i = 0; i < grade; i++) {
    		for (int j = 0; j< grade; j++) {
    			numField[i][j] = i * grade + j + 1;
    		}
    	}
    	
    	//进行随机分配
    	for (int i = 0; i < grade; i++) {
            for (int j = 0; j < grade; j++) {
                if ((i == grade - 1) && (j == grade - 1))
                    break;//最后一个不随机。
                //随机选中一个位置，和当前位置调换。
                int x = random.nextInt(grade - 1);
                int y = random.nextInt(grade - 1);
                if (x == grade - 1 && y == grade - 1) {
                    continue;
                }
                int temp = numField[i][j];
                numField[i][j] = numField[x][y];
                numField[x][y] = temp;
            }
        }
    }
    
    public String getStringTime() {
    	int second = time % 60;
		int m = time / 60;
		int minute = m % 60;
		int hour = m / 60;
		String se = "", mi = "", ho = "";
		if (second >= 10) {
			se = se + String.valueOf(second);
		} else {
			se = se + "0" + String.valueOf(second);
		}
		if (minute >= 10) {
			mi = mi + String.valueOf(minute);
		} else {
			mi = mi + "0" + String.valueOf(minute);
		}
		if (hour >= 10) {
			ho = ho + String.valueOf(hour);
		} else {
			ho = ho + "0" + String.valueOf(hour);
		}
		return ho+":"+mi+":"+se;
    }
    
    class MenuItemActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			if (cmd.equals("开始")) {
				startGame();
				startThread();
			} else if (cmd.equals("退出")) {
				System.exit(0);
			} else if (cmd.equals("暂停")) {
			    timeLabel.setText("暂停");
			    isPause = 1;
			    timer.cancel();
			    pauseItem.setText("继续");
			} else if (cmd.equals("继续")) {
				timeLabel.setText(getStringTime());
				startThread();
				isPause = 0;
				pauseItem.setText("暂停");
			} else if (cmd.equals("停止")) {
				timer.cancel();
				timeLabel.setText("");
				imagePanel.removeAll();
				imagePanel.setBackground(Color.GRAY);
				startItem.setEnabled(true);
				pauseItem.setText("暂停");
				pauseItem.setEnabled(false);
				stopItem.setEnabled(false);
			} else if (cmd.equals("玩家排行榜")) {
				RankUI r = new RankUI();
			} else if (cmd.equals("难度设置")) {
				String g = JOptionPane.showInputDialog("请设置难度（即拼图的行数）?");
				boolean isNum = true;
				if (g.equals("")) {
					grade = 3;
				} else {
					for (int i = 0; i < g.length(); i++) {  
						if (!Character.isDigit(g.charAt(i))) {
							isNum = false;
						}
					}
					if(isNum) {
						grade = Integer.parseInt(g);
					} else {
						JOptionPane.showMessageDialog(frame, "请输入数字！！","warning",JOptionPane.WARNING_MESSAGE);
					}
				}
			} else if (cmd.equals("背景音乐开")) {			
				musicItem.setText("背景音乐关");
				File f = new File("music/" + musicList[musicIndex] + ".wav");
				try {
					a = Applet.newAudioClip(f.toURI().toURL());
					a.loop();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nextMusicItem.setEnabled(true);
			} else if (cmd.equals("背景音乐关")) {
				musicItem.setText("背景音乐开");
				a.stop();
				nextMusicItem.setEnabled(false);
			} else if (cmd.equals("魔板设置开")) {
				isPic = true;
				picItem.setText("魔板设置关");
				nextPicItem.setEnabled(true);
			} else if (cmd.equals("魔板设置关")) {
				isPic = false;
				picItem.setText("魔板设置开");
				nextPicItem.setEnabled(false);
			} else if (cmd.equals("下一张背景图")) { 
				picIndex = (picIndex + 1) % 3;
			} else if (cmd.equals("下一首背景音乐")) {
				musicIndex = (musicIndex + 1) % 3;
				a.stop();
				File f = new File("music/" + musicList[musicIndex] + ".wav");
				try {
					a = Applet.newAudioClip(f.toURI().toURL());
					a.loop();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
    	
    }
    
    class ImageButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (isPause == 1) {
				
			}else {
				ImageButton btn = (ImageButton)e.getSource();
				int row = btn.getRow();
				int col = btn.getCol();
				//判断是否与空白块相邻
				if ((Math.abs(row - blankRow) + Math.abs(col - blankCol)) == 1) {
					int temp = btnField[row][col].getNum();
					System.out.println(temp + " " +btnField[blankRow][blankCol].getNum());
					btnField[row][col].setNum(btnField[blankRow][blankCol].getNum());
		            btnField[blankRow][blankCol].setNum(temp);
		            btnField[blankRow][blankCol].setImage(true, isPic);
		            btnField[row][col].setImage(false, isPic);
		            
		            temp = numField[row][col];
		            numField[row][col] = numField[blankRow][blankCol];
		            numField[blankRow][blankCol] = temp;
		            
		            blankCol = col;
		            blankRow = row;
		            
		            if (isGameOver()) {
		            	String name = JOptionPane.showInputDialog("你赢了，告诉我们你的姓名吧！");
		            	Player p = new Player(name, time, grade);
		            	PlayerMethod pm = new PlayerMethod();
		            	pm.add(p);
		            	
		            	//将游戏界面还原
		            	timeLabel.setText("");
						imagePanel.removeAll();
						imagePanel.setBackground(Color.GRAY);
						startItem.setEnabled(true);
						pauseItem.setText("暂停    Ctrl+P");
						pauseItem.setEnabled(false);
						stopItem.setEnabled(false);
		            }
				}
			}
		}
    	
    }
    
    public boolean isGameOver() {
    	for (int i = 0; i < grade; i++) {
    		for (int j = 0; j < grade; j++) {
    			if (numField[i][j] != i * grade + j + 1) {
    				return false;
    			}
    		}
    	}
    	
    	return true;
    }
    
    public class MyTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			time++;
			timeLabel.setText(getStringTime());
		}
    	
    }
    
    public void startThread() {
    	if (timer != null) {
    		timer.cancel();
    	}
    	timer = new Timer();
    	timer.scheduleAtFixedRate(new MyTask(), 1000, 1000);
    }
    
}