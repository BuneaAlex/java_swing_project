package graphical_user_interface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class application extends JFrame implements ActionListener, ItemListener, ListSelectionListener {

    private Border border_center = BorderFactory.createEmptyBorder(10,10,10,10);
    private Border border_contents = BorderFactory.createEmptyBorder(0,0,10,0);
    private Border border_list = BorderFactory.createLineBorder(Color.BLUE,1);

    private Box boxButtons = Box.createVerticalBox();
    private Box boxHidden = Box.createVerticalBox();
    private Box boxVisible = Box.createVerticalBox();
    private Box boxBorders = Box.createVerticalBox();

    private JPanel contents;
    private JPanel panel_center;
    private JPanel panel_south;

    private JButton Add;
    private JButton AddAll;
    private JButton Remove;
    private JButton RemoveAll;

    private JLabel ListHidden;
    private JLabel ListVisible;
    private JLabel ListBorder;
    private JLabel Image;
    private JLabel SelectedHidden;
    private JLabel SelectedHiddenLabel;
    private JLabel SelectedVisible;
    private JLabel SelectedVisibleLabel;
    private JLabel SelectedBorder;
    private JLabel SelectedBorderLabel;

    private JList<String> listHidden;
    private JList<String> listVisible;
    private JComboBox<String> listBorder;

    private final Font fontBold = new Font(Font.DIALOG,Font.BOLD,14);
    private final Font fontPlain = new Font(Font.DIALOG,Font.PLAIN,14);

    private String [] borders = {"Select a border","Compound","Empty","Etched","line (thin)","line (thick)","line (colored)","lowered Bevel","Matte","Raised Titled"};
    private String [] mountains = {"Apenini","Everest","Fuji","K2","Kilimanjaro","Mont Blanc","Penini","Ural"};

    private DefaultListModel<String> listModelHidden = new DefaultListModel<>();
    private DefaultListModel<String> listModelVisible = new DefaultListModel<>();
    private DefaultComboBoxModel<String> listModelBorder = new DefaultComboBoxModel<>();

    public application(){

        super("List Components");
        setFonts();

        contents = new JPanel();
        contents.setBorder(border_contents);
        contents.setLayout(new BorderLayout());
        setContentPane(contents);

        //North Region
        JLabel Title = new JLabel("List Components",SwingConstants.CENTER);
        Title.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        contents.add(Title,BorderLayout.NORTH);

        //Center Region
        panel_center = new JPanel();
        panel_center.setBorder(border_center);

        //Hidden List:
        //All components are left-aligned in a vertical box (LEFT_ALIGNMENT)
        ListHidden = new JLabel("Hidden:");
        ListHidden.setAlignmentX(Component.LEFT_ALIGNMENT);

        initHiddenModel();
        listHidden = new JList<>(listModelHidden);
        listHidden.setAlignmentX(Component.LEFT_ALIGNMENT);
        listHidden.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listHidden.setBorder(border_list);

        JScrollPane scrollHiddenList = new JScrollPane(listHidden);
        scrollHiddenList.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollHiddenList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setSpecificSize(scrollHiddenList,new Dimension(200,300));

        boxHidden.add(ListHidden);
        boxHidden.add(scrollHiddenList);
        panel_center.add(boxHidden);

        //Spacer
        panel_center.add(Box.createRigidArea(new Dimension(10,1)));

        //Buttons
        Add = new JButton("Add >");
        AddAll = new JButton("Add all >>");
        Remove = new JButton("< Remove");
        RemoveAll = new JButton("<< Remove all");

        Dimension dimRemoveAll = RemoveAll.getPreferredSize();
        setSpecificSize(Add,dimRemoveAll);
        setSpecificSize(AddAll,dimRemoveAll);
        setSpecificSize(Remove,dimRemoveAll);

        boxButtons.add(Add);
        boxButtons.add(Box.createRigidArea(new Dimension(1,5)));
        boxButtons.add(AddAll);
        boxButtons.add(Box.createRigidArea(new Dimension(1,20)));
        boxButtons.add(Remove);
        boxButtons.add(Box.createRigidArea(new Dimension(1,5)));
        boxButtons.add(RemoveAll);
        panel_center.add(boxButtons);

        //Spacer
        panel_center.add(Box.createRigidArea(new Dimension(10,1)));

        //Visible list:
        ListVisible = new JLabel("Visible:");
        ListVisible.setAlignmentX(Component.CENTER_ALIGNMENT);

        listVisible = new JList<>(listModelVisible);
        listVisible.setAlignmentX(Component.LEFT_ALIGNMENT);
        listVisible.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listVisible.setBorder(border_list);

        JScrollPane scrollVisibleList = new JScrollPane(listVisible);
        scrollHiddenList.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollVisibleList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setSpecificSize(scrollVisibleList,new Dimension(200,300));
        
        boxVisible.add(ListVisible);
        boxVisible.add(scrollVisibleList);
        panel_center.add(boxVisible);

        //Spacer
        panel_center.add(Box.createRigidArea(new Dimension(10,1)));

        //Border List
        ListBorder = new JLabel("Border:");
        ListBorder.setAlignmentX(Component.LEFT_ALIGNMENT);

        initBorderModel();
        listBorder = new JComboBox<>(listModelBorder);
        listBorder.setAlignmentX(Component.LEFT_ALIGNMENT);

        Dimension dimListView = listBorder.getPreferredSize();
        setSpecificSize(listBorder,new Dimension(200,dimListView.height));

        Image = new JLabel(new ImageIcon());
        Image.setAlignmentX(Component.LEFT_ALIGNMENT);
        setSpecificSize(Image,new Dimension(300,350));

        boxBorders.add(ListBorder);
        boxBorders.add(listBorder);
        boxBorders.add(Box.createRigidArea(new Dimension(1,20)));
        boxBorders.add(Image);
        panel_center.add(boxBorders);

        contents.add(panel_center,BorderLayout.CENTER);

        //South Region
        panel_south = new JPanel();
        SelectedHiddenLabel = new JLabel("Selected hidden:");
        SelectedHidden = new JLabel();
        SelectedVisibleLabel = new JLabel("Selected visible:");
        SelectedVisible = new JLabel();
        SelectedBorderLabel = new JLabel("Selected border:");
        SelectedBorder = new JLabel();

        panel_south.add(SelectedHiddenLabel);
        panel_south.add(SelectedHidden);
        panel_south.add(Box.createRigidArea(new Dimension(100,1)));
        panel_south.add(SelectedVisibleLabel);
        panel_south.add(SelectedVisible);
        panel_south.add(Box.createRigidArea(new Dimension(100,1)));
        panel_south.add(SelectedBorderLabel);
        panel_south.add(SelectedBorder);
        contents.add(panel_south,BorderLayout.SOUTH);

        Add.addActionListener(this);
        AddAll.addActionListener(this);
        Remove.addActionListener(this);
        RemoveAll.addActionListener(this);
        listHidden.addListSelectionListener(this);
        listVisible.addListSelectionListener(this);
        listBorder.addItemListener(this);

        //Size and display
        setSize(1050,650);
        setResizable(false);
        setLocationRelativeTo(null); //centers window
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==Add){
            addItem();
            return;
        }
        if(source==AddAll){
            addAllItems();
            return;
        }
        if(source==Remove){
            removeItem();
            return;
        }
        if(source==RemoveAll){
            removeAllItems();
        }
    }
    private void addItem(){
        int iSelected = listHidden.getSelectedIndex();
        if(iSelected==-1)
            return;
        String addedItem = listHidden.getSelectedValue();
        //removing the selected item from the hidden list
        listModelHidden.remove(iSelected);
        displaySelectedItems();
        //add to the visible list
        int size = listModelVisible.getSize();
        if(size==0){//empty list
            listModelVisible.addElement(addedItem);
            return;
        }
        //looking for a larger item
        for(int i=0;i<size;i++){
            String item = listModelVisible.elementAt(i);
            int compare = addedItem.compareToIgnoreCase(item);
            if(compare<0)//addedItem<item
            {
                listModelVisible.add(i,addedItem);
                return;
            }
        }
        //there is no larger item, so we add it to the end
        listModelVisible.addElement(addedItem);
    }
    private void addAllItems(){
        listModelVisible.clear();
        for(String s : mountains){
            listModelVisible.addElement(s);
        }
        listModelHidden.clear();
    }
    private void removeItem(){
        int iSelected = listVisible.getSelectedIndex();
        if(iSelected==-1)
            return;
        String removedItem = listVisible.getSelectedValue();
        //removing the item from the visible list
        listModelVisible.remove(iSelected);
        displaySelectedItems();
        int size = listModelHidden.getSize();
        if(size==0){
            listModelHidden.addElement(removedItem);
            return;
        }
        //looking for a larger item
        for(int i=0;i<size;i++){
            String item = listModelHidden.elementAt(i);
            int compare = removedItem.compareToIgnoreCase(item);
            if(compare<0)//removedItem<item
            {
                listModelHidden.add(i,removedItem);
                return;
            }
        }
        //there isn't a larger item, so we add it to the end of the list
        listModelHidden.addElement(removedItem);

    }
    private void removeAllItems(){
        listModelHidden.clear();
        for(String s : mountains){
            listModelHidden.addElement(s);
        }
        listModelVisible.clear();
        displaySelectedItems();
    }
    private void displaySelectedItems(){
        int iSelected;

        String itemName;
        iSelected = listHidden.getSelectedIndex();
        if(iSelected==-1)
            SelectedHidden.setText("");
        else
        {
            itemName = listHidden.getSelectedValue();
            SelectedHidden.setText(itemName);
        }

        iSelected = listVisible.getSelectedIndex();
        if(iSelected==-1)
            SelectedVisible.setText("");
        else
        {
            itemName = listVisible.getSelectedValue();
            SelectedVisible.setText(itemName);
        }

        iSelected = listBorder.getSelectedIndex();
        if(iSelected==-1)
            SelectedBorder.setText("");
        else
        {
            itemName = listBorder.getItemAt(iSelected);
            SelectedBorder.setText(itemName);
        }

    }
    private void initBorderModel(){
        for(String s : borders){
            listModelBorder.addElement(s);
        }
    }
    private void initHiddenModel(){
        for(String s : mountains){
            listModelHidden.addElement(s);
        }
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        if(source==listBorder){
            changeBorder();
            displaySelectedItems();
            return;
        }
    }
    private void setFonts(){
        UIManager.put("Button.font",fontBold);
        UIManager.put("Combobox.font",fontBold);
        UIManager.put("Label.font",fontBold);
        UIManager.put("List.font",fontBold);
    }
    private void setSpecificSize(JComponent component,Dimension dimension){
        component.setMaximumSize(dimension);
        component.setPreferredSize(dimension);
        component.setMinimumSize(dimension);
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        Object source = e.getSource();
        if(source==listHidden){
            displaySelectedItems();
            return;
        }
        if(source==listVisible){
            changeImage();
            displaySelectedItems();
            return;
        }
    }
    private void changeBorder(){
        int iSelected = listBorder.getSelectedIndex();
        if(iSelected==-1)
            return;
        switch (iSelected){
            case 0://no selection
                Image.setBorder(BorderFactory.createEmptyBorder());
                break;
            case 1://compound
                Border outside_border = BorderFactory.createRaisedBevelBorder();
                Border inside_border = BorderFactory.createLoweredBevelBorder();
                Image.setBorder(BorderFactory.createCompoundBorder(outside_border,inside_border));
                break;
            case 2://empty
                Image.setBorder(BorderFactory.createEmptyBorder());
                break;
            case 3://etched
                Image.setBorder(BorderFactory.createEtchedBorder());
                break;
            case 4://thin line
                Image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                break;
            case 5://thick line
                Image.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
                break;
            case 6://coloured line
                Image.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                break;
            case 7://lowered bevel
                Image.setBorder(BorderFactory.createLoweredBevelBorder());
                break;
            case 8://Matte
                Color matte = new Color(139,69,19);
                Image.setBorder(BorderFactory.createMatteBorder(5,20,5,20,matte));
                break;
            case 9://Raised bevel
                Image.setBorder(BorderFactory.createRaisedBevelBorder());
                break;
            case 10://titled
                Image.setBorder(BorderFactory.createTitledBorder("Border Title"));
                break;


        }
    }
    private void changeImage(){
        int iSelected = listVisible.getSelectedIndex();
        if(iSelected==-1){
            Image.setIcon(null);
            return;
        }
        String image = listVisible.getSelectedValue();
        image = "C:\\Users\\daniel bunea\\Pictures\\Saved Pictures\\"+image+".jpg";
        Image.setIcon(new ImageIcon(image));


    }
    public static void main(String[] args) {
        application GUI = new application();
        GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
