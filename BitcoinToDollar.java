// я прекрасно знаю, що це рішення несерйозне і дуже далеке від ідеального. Тут треба робити NumericField чи щось таке, але я вже не встигаю.  
// Просто думала, що вже ніяк не пройду, і ні на яке завдання не сподівалась. Сьогодні зранку лише його побачила.  
// Це мій перший код на джава і перші 3 години, що я працюю з цією мовою. Зробила наполовину інтуїтивно, тому що наразі цієї мови не знаю взагалі. 
// Якби я побачила його позавчора або вчора, воно, звичайно, було б краще, я б змогла пройти мову хоч поверхнево 

// Англійська - в формі написала, що сертифікат у мене є, але я його насправді тільки що отримала. Якраз b1  

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BitcoinToDollar {
    private static final Logger LOG = Logger.getLogger(BitcoinToDollar.class.getName());

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.add(new PanelCommon());


            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);

            frame.setVisible(true);

        });
    }
}


class PanelTextField extends JPanel {

    private JTextField textField;
    private JButton buttonRemove;
    private JPanel panelParent;

    public PanelTextField(JPanel panelParent) {
        setLayout(new BorderLayout());
        this.panelParent = panelParent;
        textField = new JTextField();
        buttonRemove = new JButton("X");
        buttonRemove.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                PanelTextField.this.panelParent.remove(PanelTextField.this);
                PanelTextField.this.panelParent.revalidate();
                PanelTextField.this.panelParent.repaint();
            });
        });

        add(textField, BorderLayout.CENTER);
        add(buttonRemove, BorderLayout.EAST);
    }

    public String getText() {
        return textField.getText();
    }
}

class PanelCommon extends JPanel {

    private JLabel label;

    private List<PanelTextField> panelTextFieldList = new ArrayList();

    private JPanel panelContainer;

    private JButton buttonShowResult;

    public PanelCommon() {
        setLayout(new BorderLayout());
        label = new JLabel("Course Calculator");
        add(label, BorderLayout.NORTH);

        panelContainer = new JPanel() {
            @Override
            public void remove(Component comp) {
                panelTextFieldList.remove(comp);
                super.remove(comp);
            }
        };
        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
        add(new JScrollPane(panelContainer), BorderLayout.CENTER);

                PanelTextField panelTextFieldBitcoin = new PanelTextField(panelContainer);
                panelTextFieldList.add(panelTextFieldBitcoin);
                panelContainer.add(panelTextFieldBitcoin);
                panelContainer.revalidate();
                PanelCommon.this.revalidate();
   
                PanelTextField panelTextFieldDollars = new PanelTextField(panelContainer);
                panelTextFieldList.add(panelTextFieldDollars);
                panelContainer.add(panelTextFieldDollars);
                panelContainer.revalidate();
                PanelCommon.this.revalidate();

        buttonShowResult = new JButton("Calculate Result");
        buttonShowResult.addActionListener(e -> {
            StringBuilder stringBuilder = new StringBuilder();
 
				  String textB = panelTextFieldBitcoin.getText();
				  String textD = panelTextFieldDollars.getText();
				  if (isNumeric(textB)) {
				  if (isNumeric(textD)) {
				  double dBitcoin = Double.parseDouble(textB);
				  double dDollars = Double.parseDouble(textD);
				  double res = dDollars/dBitcoin;
				  stringBuilder.append(res + "\n" + panelTextFieldDollars.getText());
                  }
				  }
            JOptionPane.showMessageDialog(PanelCommon.this, stringBuilder.toString());

        });

        JPanel panel = new JPanel();
        panel.add(buttonShowResult);
        add(panel, BorderLayout.SOUTH);
    }
	
	public static boolean isNumeric(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}

}
