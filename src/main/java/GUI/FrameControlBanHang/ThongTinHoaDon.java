package GUI.FrameControlBanHang;

import GUI.FrameControlBaoCao.ReportHoadon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


public class ThongTinHoaDon extends JFrame {
    private JTable table;

    private final int maHoaDon;

    public ThongTinHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(860, 420);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.FrameControlCongcu.TimMaSach.class.getResource("/data/img/kb.png")));
        setTitle("Chi tiết");
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 816, 330);
        getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        LoadTable();

        JButton bOK = new JButton("OK");
        bOK.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        bOK.setBounds(450, 350, 120, 25);
        bOK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bOK.setContentAreaFilled(false);
        getContentPane().add(bOK);

        JButton bIn = new JButton("In hóa đơn");
        bIn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        bIn.setBounds(275, 350, 120, 25);
        bIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bIn.setContentAreaFilled(false);
        getContentPane().add(bIn);

        bOK.addActionListener(e -> dispose());

        bIn.addActionListener(e -> {
            ReportHoadon viewer = new ReportHoadon("Report/rp_Hoadon.jasper", maHoaDon);
            viewer.setVisible(true);
            bOK.doClick();
        });
    }

    public void LoadTable() {
        try {

            Statement statement = ketnoi.ConnectDB.getConnection().createStatement();
            String sql = "SELECT " +
                    " mahoadon, tensach, tentacgia, soluong, mucgiamgia " +
                    " FROM chitiethoadon " +
                    " left join sach on chitiethoadon.masach = sach.masach " +
                    " left join tacgia on sach.matacgia = tacgia.matacgia" +
                    " WHERE " +
                    " mahoadon = " + maHoaDon;
            ResultSet rs = statement.executeQuery(sql);
            Vector col = new Vector();
            Vector data = new Vector();
            col.add("Mã hóa đơn");
            col.add("Tên sách");
            col.add("Tác giả");
            col.add("Số lượng mua");
            col.add("Mức giảm giá (%)");


            while (rs.next()) {
                Vector row = new Vector();
                for (int i = 1; i < 6; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            DefaultTableModel dataModel = new DefaultTableModel(data, col) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table.setModel(dataModel);
            table.getColumnModel().getColumn(0).setPreferredWidth(15);
            table.getColumnModel().getColumn(1).setPreferredWidth(150);
            table.getColumnModel().getColumn(2).setPreferredWidth(150);
            table.getColumnModel().getColumn(3).setPreferredWidth(15);
            table.getColumnModel().getColumn(4).setPreferredWidth(15);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ThongTinHoaDon(5).setVisible(true);
    }

    public void setSelected(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
