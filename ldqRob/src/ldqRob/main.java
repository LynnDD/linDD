package ldqRob;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Area;
import java.awt.image.PixelGrabber;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import java.awt.event.*;
import com.sun.awt.AWTUtilities;

/*
 * 能够修改的地方：
 * 右键:
 *  添加右键一级菜单 -> 添加右键二级菜单
 * 
 */

public class main extends JFrame {
    private static final long serialVersionUID = 1L;
	static List<task> tasks = new ArrayList<task>();
	static task currunt_task;
	task_lib tb;
    static Thread alarm_clock_all = new alarm_clock();
    Color colors[] = new Color[]{
    		new Color(199, 237, 233), 
    		new Color(175, 215, 237), 
    		new Color(92, 167, 186), 
    		new Color(255, 66, 93), 
    		new Color(240, 240, 240)};
    private Point origin_frame; // 用于移动窗体
    private Image img; // 用来设定窗体不规则样式的图片
	Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
	int screen_width = (int)screensize.getWidth();
	int screen_height = (int)screensize.getHeight();

    public main() {
        super();
        MediaTracker mt = new MediaTracker(this);
        img = Toolkit.getDefaultToolkit().createImage("src//other//main.png");
        mt.addImage(img, 0);
        try {
            mt.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        core_ini();
        try {
            frame_ini();// 窗体初始化
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void core_ini() {
    	tb = new task_lib();
        //数据
    	tasks.clear();
	    tb.open(tasks);
	    currunt_task = null;
	    for(task t:tasks) {
	    	if(((alarm_clock) alarm_clock_all).check_time_over(t.timeOut)) {
	    		continue;
	    	} else {
	    		currunt_task = t;
	    		break;
	    	}
	    }
	    if(!(currunt_task == null)) {
	        ((alarm_clock) alarm_clock_all).ini();
	        String temp = new String();
	        for(task t:tasks) {
	        	temp= temp + t.massage + "\n";
	        }
	        JOptionPane.showConfirmDialog(null,  "アクティブタスク:\n\"" + temp, "\"ピカチュウ", JOptionPane.PLAIN_MESSAGE);
	        alarm_clock_all.start();
	    } else {
	    	JOptionPane.showConfirmDialog(null,  "今天任务都完成了", "\"ピカチュウ", JOptionPane.PLAIN_MESSAGE);
	    }

	}

	@SuppressWarnings("unchecked")
	private void frame_ini() throws IOException {
        
        JFrame myframe1 = new JFrame();
        JPanel jpanel_background = new JPanel();
	    JPanel jpanel_r_menu = new JPanel(new GridLayout(2,1));
	    JPanel jpanel_r_schedule = new JPanel(new BorderLayout());
    	Border boder = BorderFactory.createLineBorder(Color.red);  
    	Border b = BorderFactory.createTitledBorder(boder, null); 
		@SuppressWarnings("rawtypes")
		DefaultListModel model_list=new DefaultListModel();
		
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                origin_frame.x = e.getX();
                origin_frame.y = e.getY();
            }

            // 窗体上单击鼠标右键关闭程序
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                	jpanel_r_menu.setVisible(true);
                	jpanel_r_schedule.setVisible(false); 
                	Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
                	myframe1.setLocation(point.x, point.y);
                	myframe1.setVisible(true);
                	myframe1.pack();
            			}
                else if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                	if(currunt_task == null) {
                		JOptionPane.showConfirmDialog(null,  "今天任务都完成了", "\"ピカチュウ", JOptionPane.PLAIN_MESSAGE);
                	} else {
                    	task_in_list();
                	}
                }else if(e.getButton() == MouseEvent.BUTTON1) {
                	main.this.setAlwaysOnTop(true);
                	jpanel_r_menu.setVisible(true);
    				jpanel_r_schedule.setVisible(false); 
    				myframe1.setVisible(false);
    				myframe1.pack();
                }
               
            }
        });
		
        //jpanel_r_menu
    	Dimension preferredSize = new Dimension(100,20);//设置尺寸
    	JButton bitn_schedule = new JButton("备忘录");
    	bitn_schedule.setPreferredSize(preferredSize);
    	bitn_schedule.setFocusPainted(false);
    	bitn_schedule.setBackground(colors[4]); 
        bitn_schedule.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model_list.clear();
		    	for(int i=0;i<tasks.size();i++){
		    		model_list.addElement(tasks.get(i).massage);
		    	}
		    	model_list.addElement(" ");
		    	jpanel_r_menu.setVisible(false);
				jpanel_r_schedule.setVisible(true); 
				myframe1.pack();
            	Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
            	int x, y;
				if((point.x + jpanel_r_schedule.getWidth()) > screen_width) {
					x = point.x - jpanel_r_schedule.getWidth();
				} else {
					x = point.x;
				}
				if((point.y + jpanel_r_schedule.getHeight()) > screen_height) {
					y = point.y - jpanel_r_schedule.getHeight();
				} else {
					y = point.y;
				}
				myframe1.setLocation(x, y);

			}
    	}); 
        
        JButton bitn_quit = new JButton("退出");
        bitn_quit.setPreferredSize(preferredSize);
        bitn_quit.setFocusPainted(false);
        bitn_quit.setBackground(colors[4]); 
        bitn_quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((alarm_clock) alarm_clock_all).clock_stop();
				try {
					alarm_clock_all.join();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.exit(0); 
			}
    	});
        
    	jpanel_r_menu.setPreferredSize(new Dimension(100, 60));
    	jpanel_r_menu.setBorder(b);  
    	//jpanel_r_menu.setBorder(new EmptyBorder(10, 10, 10, 10));
    	jpanel_r_menu.add(bitn_schedule);
    	jpanel_r_menu.add(bitn_quit);
    	jpanel_r_menu.setVisible(true);
    	
        //jpanel_r_schedule     
        @SuppressWarnings("rawtypes")
		JList list = new JList();
        list.setBackground(colors[4]);
    	list.setModel(model_list);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisible(true);
    	
    	JButton bitn_schedule_mod = new JButton("备忘修改");
    	bitn_schedule_mod.setPreferredSize(preferredSize);
    	bitn_schedule_mod.setFocusPainted(false);
    	bitn_schedule_mod.setBackground(colors[1]);
    	bitn_schedule_mod.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        try {
		        	Runtime runtime = Runtime.getRuntime();
		            runtime.exec("explorer /select, " + task_lib.pathname);
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
			}
    	}); 
    	
    	JButton bitn_schedule_save = new JButton("备忘生效");
    	bitn_schedule_save.setPreferredSize(preferredSize);
    	bitn_schedule_save.setFocusPainted(false);
    	bitn_schedule_save.setBackground(colors[1]);
    	bitn_schedule_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				core_ini();
				model_list.clear();
		    	for(int i=0;i<tasks.size();i++){
		    		model_list.addElement(tasks.get(i).massage);
		    	}
		    	model_list.addElement(" ");
				jpanel_r_schedule.setVisible(true); 
				myframe1.pack();
			}
    	}); 
    	
    	JButton bitn_schedule_back = new JButton("返回");
    	bitn_schedule_back.setPreferredSize(preferredSize);
    	bitn_schedule_back.setFocusPainted(false);
    	bitn_schedule_back.setBackground(colors[4]);
    	bitn_schedule_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		    	jpanel_r_schedule.setVisible(false);
		    	jpanel_r_menu.setVisible(true);
		    	myframe1.pack();
			}
    	}); 
    	
    	//jpanel_r_schedule.setBorder(new EmptyBorder(5, 5, 5, 5));
    	jpanel_r_schedule.add(list, BorderLayout.NORTH);
    	jpanel_r_schedule.add(bitn_schedule_mod, BorderLayout.CENTER);
    	jpanel_r_schedule.add(bitn_schedule_save, BorderLayout.EAST);
    	jpanel_r_schedule.add(bitn_schedule_back, BorderLayout.SOUTH);
    	jpanel_r_schedule.setVisible(false); 
    	//右键JFrame
    	myframe1.setUndecorated(true);  
    	jpanel_background.setBackground(colors[0]);
    	jpanel_background.add(jpanel_r_menu, BorderLayout.WEST);
    	jpanel_background.add(jpanel_r_schedule, BorderLayout.EAST);
    	myframe1.add(jpanel_background);
    	myframe1.setResizable(false);
    	myframe1.setVisible(false);
    	myframe1.setAlwaysOnTop(true);
    	myframe1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	myframe1.pack();
	    myframe1.setAlwaysOnTop(true);
    	//主屏幕

	    this.setSize(img.getWidth(null), img.getHeight(null));
        this.setUndecorated(true);
        this.origin_frame = new Point();
        this.setAlwaysOnTop(true);
        AWTUtilities.setWindowShape(this, getImageShape(img));
        AWTUtilities.setWindowOpacity(this, 1.0f);
        this.setLocationRelativeTo(null);

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - origin_frame.x, p.y + e.getY()
                        - origin_frame.y);
            }
        });

    }
	
	private void task_in_list() {
        int n = JOptionPane.showConfirmDialog(null, "アクティブタスク:\n\"" + currunt_task.massage  + "\"是否已经完成", "ピカチュウ",JOptionPane.YES_NO_OPTION);//i=0/1 
		if(n == 0) {
			int index = currunt_task.id;
			currunt_task = tasks.get(index);
			((alarm_clock) alarm_clock_all).ini();
			JOptionPane.showConfirmDialog(null,  "アクティブタスク:\n\"" + currunt_task.massage, "ピカチュウ", JOptionPane.PLAIN_MESSAGE);
		}
		
	}

    public Shape getImageShape(Image img) {
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        int width = img.getWidth(null);// 图像宽度
        int height = img.getHeight(null);// 图像高度

        // 筛选像素
        // 首先获取图像所有的像素信息
        PixelGrabber pgr = new PixelGrabber(img, 0, 0, -1, -1, true);
        try {
            pgr.grabPixels();
        } catch (InterruptedException ex) {
            ex.getStackTrace();
        }
        int pixels[] = (int[]) pgr.getPixels();

        // 循环像素
        for (int i = 0; i < pixels.length; i++) {
            // 筛选，将不透明的像素的坐标加入到坐标ArrayList x和y中
            int alpha = getAlpha(pixels[i]);
            if (alpha != 0) {
                x.add(i % width > 0 ? i % width - 1 : 0);
                y.add(i % width == 0 ? (i == 0 ? 0 : i / width - 1) : i / width);
            }
        }
        // 建立图像矩阵(0为透明,1为不透明)
        int[][] matrix = new int[height][width];
        // 导入坐标ArrayList中的不透明坐标信息
        for (int c = 0; c < x.size(); c++) {
            matrix[y.get(c)][x.get(c)] = 1;
        }
        /*
         * 由于Area类所表示区域可以进行合并，我们逐一水平"扫描"图像矩阵的每一行，
         * 将不透明的像素生成为Rectangle，再将每一行的Rectangle通过Area类的rec
         * 对象进行合并，最后形成一个完整的Shape图形
         */
        Area rec = new Area();
        int temp = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 1) {
                    if (temp == 0)
                        temp = j;
                    else if (j == width) {
                        if (temp == 0) {
                            Rectangle rectemp = new Rectangle(j, i, 1, 1);
                            rec.add(new Area(rectemp));
                        } else {
                            Rectangle rectemp = new Rectangle(temp, i,
                                    j - temp, 1);
                            rec.add(new Area(rectemp));
                            temp = 0;
                        }
                    }
                } else {
                    if (temp != 0) {
                        Rectangle rectemp = new Rectangle(temp, i, j - temp, 1);
                        rec.add(new Area(rectemp));
                        temp = 0;
                    }
                }
            }
            temp = 0;
        }
        return rec;
    }

    private int getAlpha(int pixel) {
        return (pixel >> 24) & 0xff;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, 0, 0, null);
    }

    public static void main(String[] args) {
        main sample = new main();
        sample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sample.setVisible(true);
    }
}