package KIOSK;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import javax.imageio.ImageIO;

public class Kiosk extends JFrame {

  Font font1 = new Font(Font.SANS_SERIF, Font.BOLD, 14); //메뉴를 표시할 폰트


  int count;
  int total = 0; //총 결제 금액

  String contents = ""; //선택된 메뉴
  JSlider[] sl = new JSlider[3]; //tab

  Panel LogoPanel = new Panel();


  private JRadioButton[] MD = new JRadioButton[3];
  private String[] names = { "머그", "텀블러", "접시" };
  private JLabel sumLabelText;
  private ImageIcon[] imageList;
  private ImageIcon[] selectedImageList;

  public Kiosk() {
      JFrame frame = new JFrame("스타벅스 동국 키오스크");
      

      //커피 메뉴
      String espresso[] = { "아메리카노(Ice)", "아메리카노(Hot)", "카페라떼(Ice)", "카페라떼(Hot)", "카페모카(Ice)", "카페모카(HOT)", "마끼아또(Ice)", "마끼아또(Hot)", "카푸치노(Ice)"};
      int espresso_price[] = { 4500, 4500, 5000, 5000, 5500, 5500, 5000, 5000, 5000};

      //음료 메뉴
      String beverage[] = { "망고패션티블렌디드", "얼그레이블렌디드", "딸기요거트블렌디드", "망고바나나블렌디드", "코튼스카이요거트", "딸기아사이 리프레셔", "유자패션 피지오", "쿨라임 피지오", "피치딸기 피지오" };
      int beverage_price[] = { 6000, 6000, 6500, 6500, 6500, 6000, 6500, 6500, 6500 };

      //시즌 메뉴
      String season[] = { "더블에스프레소 크림(Ice)", "더블에스프레소 크림(Hot)", "토피넛라떼(Ice)", "토피넛라떼(Hot)", "핑크캐모마일릴렉서(Ice)", "핑크캐모마일릴렉서(Hot)", "루비 뱅쇼(Ice)","루비 뱅쇼(Hot)", "딸기 라떼(Ice)"
              };
      int season_price[] = { 7000, 7000, 7500, 6500, 6500, 7000, 7500, 7500, 7500 };

      // 동국대 로고 패널

      LogoPanel.setBackground(Color.WHITE);
      LogoPanel.setLayout(new FlowLayout());

      //동국대 로고 이미지 resize
      try {
        BufferedImage LogoImage = ImageIO.read(new File("src/KIOSK/menu/dongguk_Logo.jpg"));
        Image ScaledLogoImage = LogoImage.getScaledInstance(150, 50, Image.SCALE_SMOOTH);
        ImageIcon Logo = new ImageIcon(ScaledLogoImage);
        JLabel logo = new JLabel(Logo);
        LogoPanel.add(logo);
      } catch (Exception e) {
          e.printStackTrace();
      }

      //커피 패널 생성
      Panel EspressoPanel = new Panel();
      EspressoPanel.setLayout(null);
      EspressoPanel.setBackground(Color.white);

      //음료 패널 생성
      Panel BeveragePanel = new Panel();
      BeveragePanel.setLayout(null);
      BeveragePanel.setBackground(Color.white);

      //시즌 메뉴 패널 생성
      Panel SeasonPanel = new Panel();
      SeasonPanel.setLayout(null);
      SeasonPanel.setBackground(Color.white);

      //커피 메뉴 버튼 및 레이블 생성
      JButton espressoButtons[] = new JButton[espresso.length];
      Label won[] = new Label[espresso.length];
      Label name[] = new Label[espresso.length];

      //음료 메뉴 버튼 및 레이블 생성
      JButton beverageButtons[] = new JButton[beverage.length];
      Label beverageWon[] = new Label[beverage.length];
      Label beverageName[] = new Label[beverage.length];

      //시즌 메뉴 버튼 및 레이블 생성
      JButton season_offButtons[] = new JButton[season.length];
      Label season_offWon[] = new Label[season.length];
      Label season_offName[] = new Label[season.length];

      //커피 메뉴 버튼 이미지 resize
      for (int i = 0; i < espresso.length; i++) {
        try {
          BufferedImage originalImage = ImageIO.read(new File("src/KIOSK/menu/espresso/" + (i + 1) + ".jpeg"));
          Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
          espressoButtons[i] = new JButton(new ImageIcon(scaledImage));

        } catch (Exception e) {
          System.out.println("이미지 불러오기 실패");
        }
      }

      //커피 메뉴 버튼 위치 설정
      for (int i = 0; i < espresso.length; i++) {
        if (i < 3) {
          espressoButtons[i].setBounds(25 + i * 150, 40, 100, 100);
        } else if (i < 6) {
          espressoButtons[i].setBounds(25 + (i - 3) * 150, 200, 100, 100);
        } else {
          espressoButtons[i].setBounds(25 + (i - 6) * 150, 360, 100, 100);
        }

        //커피 메뉴 이름 레이블 설정
        name[i] = new Label(espresso[i]);
        name[i].setFont(font1);
        name[i].setBounds(espressoButtons[i].getX(), espressoButtons[i].getY() - 20, 115, 20);
        
        //커피 메뉴 가격 레이블 설정
        won[i] = new Label(espresso_price[i] + "원");
        won[i].setBounds(espressoButtons[i].getX() + 30, espressoButtons[i].getY() + 115, 100, 20);
        
        EspressoPanel.add(name[i]);
        EspressoPanel.add(espressoButtons[i]);
        EspressoPanel.add(won[i]);
          
          
      }
      //음료 메뉴 버튼 이미지 resize
      for (int i = 0; i < beverage.length; i++) {
        try {
          BufferedImage originalImage = ImageIO.read(new File("src/KIOSK/menu/beverage/" + (i + 1) + ".jpeg"));
          Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
          beverageButtons[i] = new JButton(new ImageIcon(scaledImage));
            
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      
      //음료 메뉴 버튼 위치 설정
      for (int i = 0; i < beverage.length; i++) {
        if (i < 3) {
          beverageButtons[i].setBounds(25 + i * 150, 40, 100, 100);
        } else if (i < 6) {
          beverageButtons[i].setBounds(25 + (i - 3) * 150, 200, 100, 100);
        } else {
          beverageButtons[i].setBounds(25 + (i - 6) * 150, 360, 100, 100);
        }
        
        //음료 메뉴 이름 레이블 설정
        beverageName[i] = new Label(beverage[i]);
        beverageName[i].setFont(font1);
        beverageName[i].setBounds(beverageButtons[i].getX(), beverageButtons[i].getY() - 20, 115, 20);
        //음료 메뉴 가격 레이블 설정
        beverageWon[i] = new Label(beverage_price[i] + "원");
        beverageWon[i].setBounds(beverageButtons[i].getX() + 30, beverageButtons[i].getY() + 115, 100, 20);
        //음료 패널에 버튼, 레이블 추가
        BeveragePanel.add(beverageName[i]);
        BeveragePanel.add(beverageButtons[i]);
        BeveragePanel.add(beverageWon[i]);

      }
      BeveragePanel.setPreferredSize(new Dimension(475, 1000));

      //시즌 메뉴 버튼 이미지 리사이징
      for (int i = 0; i < season.length; i++) {
        try {
          BufferedImage originalImage = ImageIO.read(new File("src/KIOSK/menu/season/" + (i + 1) + ".jpeg"));
          Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
          season_offButtons[i] = new JButton(new ImageIcon(scaledImage));

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      //시즌 메뉴 버튼 위치 설정
      for (int i = 0; i < season.length; i++) {
        if (i < 3) {
          season_offButtons[i].setBounds(25 + i * 150, 40, 100, 100);
        } else if (i < 6) {
          season_offButtons[i].setBounds(25 + (i - 3) * 150, 200, 100, 100);
        } else {
          season_offButtons[i].setBounds(25 + (i - 6) * 150, 360, 100, 100);
        }
        //시즌 메뉴 이름 레이블 설정
        season_offName[i] = new Label(season[i]);
        season_offName[i].setFont(font1);
        season_offName[i].setBounds(season_offButtons[i].getX(), season_offButtons[i].getY() - 20, 115, 20);
        //시즌 메뉴 가격 레이블 설정
        season_offWon[i] = new Label(season_price[i] + "원");
        season_offWon[i].setBounds(season_offButtons[i].getX() + 30, season_offButtons[i].getY() + 115, 100, 20);
        //시즌 패널에 버튼, 레이블 추가
        SeasonPanel.add(season_offName[i]);
        SeasonPanel.add(season_offButtons[i]);
        SeasonPanel.add(season_offWon[i]);
      }


      //Cart Panel 생성
      Panel CartPanel = new Panel();
      TextArea txt = new TextArea("");

      String[][] data = new String[0][0];
      String[] title = { "상품명", "가격"};

      DefaultTableModel model = new DefaultTableModel(data, title);
      JTable table = new JTable(model);
      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane = new JScrollPane(table);
      scrollPane.setPreferredSize(new Dimension(350, 100));
      CartPanel.add(scrollPane);

      Panel SelectPanel = new Panel();
      SelectPanel.setLayout(new GridLayout(2, 1, 0, 2));


      //목록 삭제하는 버튼
      JButton delete = new JButton("목록 삭제");

      //목록 초기화하는 버튼
      JButton deleteAll = new JButton("초기화");

      //주문하기 버튼
      JButton pay = new JButton("총 가격: "+total +"원 주문하기");

      //selectPanel에 버튼저장
      SelectPanel.add(delete);
      SelectPanel.add(deleteAll);

      //CartPanel에 selectPanel 저장
      CartPanel.add(SelectPanel);


      //목록 삭제버튼 eventListener
      delete.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (table.getSelectedRow() == -1) {
              JOptionPane.showMessageDialog(null, "삭제할 항목을 선택해주세요.");
            } else {
            int answer = JOptionPane.showConfirmDialog(null, "선택한 항목을 삭제하시겠습니까?", "메뉴 취소", JOptionPane.YES_NO_OPTION);
              if (answer == JOptionPane.YES_OPTION) {
                String valueWithWon = (String) table.getValueAt(table.getSelectedRow(), 1);
                // 가격에서 "원" 제거
                String valueWithoutWon = valueWithWon.replaceAll("[^\\d]", "");

                // 정수형으로 변환
                int value = Integer.parseInt(valueWithoutWon);
                total = total - value;
                model.removeRow(table.getSelectedRow());
                pay.setText("총 가격: " + total + "원 주문하기");
              }
            }
          }
      });


      //목록 초기화버튼 eventListener
      deleteAll.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              model.setNumRows(0); // 주문 내역 초기화
              txt.setText("");
              total = 0;
              pay.setText("총 가격: " + total + "원 주문하기");
          }
      });

      pay.setPreferredSize(new Dimension(200, 50));


      // 버튼의 배경색 설정
      pay.setBackground(new Color(27,60,53)); 

      pay.setOpaque(true); // 배경색 적용을 위해 불투명 설정
      pay.setBorderPainted(false); // 테두리를 그리지 않도록 설정 (선택적)
      pay.setContentAreaFilled(true); // 내용 영역 채우기 활성화
      pay.setFont(new Font("SansSerif", Font.BOLD, 20));
      // 버튼의 글꼴 색상 설정 (선택적)
      pay.setForeground(Color.WHITE); // 흰색 글꼴, 필요에 따라 변경 가능

      // 주문하기버튼 eventListner
      pay.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

          int answer = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?","결제하기", JOptionPane.YES_NO_OPTION);
          if (answer == JOptionPane.YES_OPTION) {
            if (total == 0) {
              JOptionPane.showMessageDialog(null, "상품을 선택해주세요!");
            }else {
              for (int i = 0; i < table.getRowCount(); i++) {
                txt.append(table.getValueAt(i, 0) + " " + table.getValueAt(i, 1) +"\n");
              }
              contents = txt.toString();

              JOptionPane.showMessageDialog(null,
                "<html><body style='text-align: center;'>"
                + "-----------------------------------<br>"
                // + htmlText
                // + "<br><br><br>"
                + "<br>총 금액:           " + total + "원"
                + "<br><br>"
                + "결제되었습니다."
                + "<br>"
                + "영수증을 확인해주세요."
                + "<br>"
                + "--------------------------------"
                + "<br>"
                +   "</body></html>");               
              try (FileWriter fr = new FileWriter("src/KIOSK/menu/dongguk_receipt.txt", true)) {
                fr.write(
                  "\n---------------영수증---------------\n"+
                  txt.getText() +
                  "\n총 금액:           " + total + "원" +
                  "\n"+
                  "\n"+
                  "-----------------------------------\n"
                  );
              } catch (Exception e1) {
                System.out.println("입출력 오류");
              }
              txt.setText("");

              }
          } else
              JOptionPane.showMessageDialog(null, "계산을 취소했습니다.\n");
        }
    });
      

      for (int i = 0; i < espresso.length; i++) {
        int j = i;

        espressoButtons[i].addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            count = 1;
            total = total + espresso_price[j];

            String Selected_Menu[] = new String[3];

            Selected_Menu[0] = espresso[j];
            Selected_Menu[1] = espresso_price[j] + "원";
            Selected_Menu[2] = total + "원";
            model.addRow(Selected_Menu);


            count = 0;
            pay.setText("총 가격: " + total + "원 주문하기");
          }
        });

      }

      for (int i = 0; i < beverage.length; i++) {
        int j = i;

        beverageButtons[i].addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            count = 1;
            total = total + beverage_price[j];

            String Selected_Menu[] = new String[3];

            Selected_Menu[0] = beverage[j];
            Selected_Menu[1] = beverage_price[j] + "원";
            Selected_Menu[2] = total + "원";
            model.addRow(Selected_Menu);

            count = 0;
            pay.setText("총 가격: " + total + "원 주문하기");

          }
        });

      }

      for (int i = 0; i < season.length; i++) {
        int j = i;
        season_offButtons[i].addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            count = 1;
            total = total + season_price[j];

            String Selected_Menu[] = new String[3];

            Selected_Menu[0] = season[j];
            Selected_Menu[1] = season_price[j] + "원";
            Selected_Menu[2] = total + "원";
            model.addRow(Selected_Menu);

            count = 0;
            pay.setText("총 가격: " + total + "원 주문하기");
          }
        });
      }

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


      //MD패널
      Panel MDPanel = new Panel();
      MDPanel.setSize(getPreferredSize());
      MDPanel.setBackground(Color.white);

      //MD패널 라디오버튼 안의 이미지아이콘
      ImageIcon mug = new ImageIcon("src/KIOSK/menu/MUG.jpeg");
      ImageIcon selectedMug = new ImageIcon("src/KIOSK/menu/selectedMug.jpeg");

      ImageIcon tumbler = new ImageIcon("src/KIOSK/menu/tumbler.jpeg");
      ImageIcon selectedTumbler = new ImageIcon("src/KIOSK/menu/selectedTumbler.jpeg");

      ImageIcon plate = new ImageIcon("src/KIOSK/menu/plate.jpeg");
      ImageIcon selectedPlate = new ImageIcon("src/KIOSK/menu/selectedPlate.jpeg");

      mug = resize(mug);
      tumbler = resize(tumbler);
      plate = resize(plate);

      selectedMug = resize(selectedMug);
      selectedTumbler = resize(selectedTumbler);
      selectedPlate = resize(selectedPlate);

      imageList = new ImageIcon[] { mug, tumbler, plate };
      selectedImageList = new ImageIcon[] { selectedMug, selectedTumbler, selectedPlate };

      ButtonGroup bg = new ButtonGroup();

      MyItemListener listener = new MyItemListener();
      for (int i = 0; i < MD.length; i++) {
        MD[i] = new JRadioButton(names[i], imageList[i]);
        MD[i].setBorderPainted(true);
        MD[i].setSelectedIcon(selectedImageList[i]);
        bg.add(MD[i]);
        MDPanel.add(MD[i],BorderLayout.CENTER );
        MD[i].addItemListener(listener);
      }
      
      MDPanel.add(new JLabel("머그 세트 12000원, 텀블러 26000원, 접시 9000원"));

      sumLabelText = new JLabel("현재 0 원 입니다.");
      MDPanel.add(sumLabelText);


      //남쪽에 배치할 패널 생성
      JPanel southJPanel = new JPanel();
      southJPanel.setLayout(new BorderLayout());

      //카트패널과 주문하기버튼을 수직으로 배치
      southJPanel.add(CartPanel,BorderLayout.CENTER);
      southJPanel.add(pay,BorderLayout.SOUTH);


      frame.add(LogoPanel, BorderLayout.NORTH);
      JTabbedPane Kiosk = new JTabbedPane();
      Kiosk.setBackground(Color.LIGHT_GRAY);
      Kiosk.addTab("ESPRESSO", EspressoPanel);
      Kiosk.addTab("BEVERAGE", BeveragePanel);
      Kiosk.addTab("SEASON", SeasonPanel);
      Kiosk.addTab("MD", MDPanel);
      frame.add(Kiosk, BorderLayout.CENTER);
      frame.add(southJPanel, BorderLayout.SOUTH);
      frame.setSize(475, 1000);
      frame.setVisible(true);
  
  
  }

  public static void main(String[] args) {
    new Kiosk();
  }

  public ImageIcon resize(ImageIcon icon) {
  Image image = icon.getImage();
  Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
  return new ImageIcon(scaledImage);
  }

  class MyItemListener implements ItemListener {
    private int sum = 0;
    
  //MD패널에 쓰이는 함수 
  public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED) {
      if (e.getItem() == MD[0])
          sum += 12000;
      else if (e.getItem() == MD[1])
          sum += 26000;
      else
          sum += 9000;
    } else {
    if (e.getItem() == MD[0])
        sum -= 12000;
    else if (e.getItem() == MD[1])
        sum -= 26000;
    else
        sum -= 9000;
    }
    sumLabelText.setText("현재 " + sum + "원 입니다.");
  }

}
}