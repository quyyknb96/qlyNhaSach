package GUI.FrameControlBanHang;

import util.ValidateUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


public class HoaDon extends JFrame {

    private JTextField tfTenKhachHang;
    private JTextField tfNgayLap;
    private JTextField tfMaSach;
    private JTextField tfSoLuong;
    private JTable table;
    Vector tableRecords = new Vector();
    Vector<String> tableTitle = new Vector();
    private JTextField tfmasach;
    private JTextField tfsoluong;
    JLabel lbTenSach, lbTenTacGia;
    JButton bTinhTien;
    DefaultListModel modelList = new DefaultListModel();
    JList list = new JList(modelList);
    int dem = 0;
    long tien[];
    long tongTien = 0;
    int maHD;
    int i;
    int mucgiamgia;
    DefaultTableModel modelTable = new DefaultTableModel(tableRecords, tableTitle);

    public HoaDon() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 420);
        getContentPane().setLayout(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.FrameControlCongcu.TimMaSach.class.getResource("/data/img/hoadon.png")));
        setTitle("Lập hóa đơn");
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Thông tin hóa đ\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 11, 285, 151);
        getContentPane().add(panel);
        panel.setLayout(null);

        tfTenKhachHang = new JTextField();
        tfTenKhachHang.setColumns(10);
        tfTenKhachHang.setBounds(110, 27, 165, 20);
        panel.add(tfTenKhachHang);

        tfNgayLap = new JTextField();
        tfNgayLap.setColumns(10);
        tfNgayLap.setBounds(110, 58, 118, 20);
        panel.add(tfNgayLap);

        tfMaSach = new JTextField();
        tfMaSach.setColumns(10);
        tfMaSach.setBounds(110, 89, 118, 20);
        panel.add(tfMaSach);

        tfSoLuong = new JTextField();
        tfSoLuong.setColumns(10);
        tfSoLuong.setBounds(110, 120, 118, 20);
        panel.add(tfSoLuong);

        JLabel lbTenKH = new JLabel("Tên khách hàng*");
        lbTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbTenKH.setBounds(10, 29, 105, 15);
        panel.add(lbTenKH);

        JLabel lbNgayLap = new JLabel("Ngày lập");
        lbNgayLap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbNgayLap.setBounds(10, 61, 74, 15);
        panel.add(lbNgayLap);

        JLabel lbMaSach = new JLabel("Mã sách*");
        lbMaSach.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbMaSach.setBounds(10, 92, 74, 15);
        panel.add(lbMaSach);

        JLabel lbSoLuong = new JLabel("Số lượng");
        lbSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbSoLuong.setBounds(10, 123, 74, 15);
        panel.add(lbSoLuong);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(298, 18, 175, 184);
        getContentPane().add(scrollPane);
        scrollPane.setViewportView(list);

        JScrollPane jsp = new JScrollPane();
        jsp.setBounds(10, 208, 463, 169);
        getContentPane().add(jsp);

        table = new JTable();
        tableTitle.add("Mã sách");
        tableTitle.add("Tên sách");
        tableTitle.add("Số lượng");
        table.setModel(modelTable);
        jsp.setViewportView(table);


        JButton bReset = new JButton("Reset");
        bReset.setBounds(483, 208, 200, 38);
        bReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bReset.setContentAreaFilled(false);
        getContentPane().add(bReset);

        JButton bXoa = new JButton("Xóa");
        bXoa.setBounds(483, 251, 200, 38);
        bXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bXoa.setContentAreaFilled(false);
        getContentPane().add(bXoa);

        JPanel p1 = new JPanel();
        p1.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Th\u00F4ng tin s\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        p1.setBounds(483, 11, 200, 102);
        getContentPane().add(p1);
        p1.setLayout(null);

        lbTenSach = new JLabel("");
        lbTenSach.setBounds(10, 25, 180, 21);
        p1.add(lbTenSach);

        lbTenTacGia = new JLabel("");
        lbTenTacGia.setBounds(10, 58, 180, 21);
        p1.add(lbTenTacGia);

        JPanel p2 = new JPanel();
        p2.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        p2.setBounds(483, 119, 200, 83);
        getContentPane().add(p2);
        p2.setLayout(null);

        JLabel lbmasach = new JLabel("Mã sách");
        lbmasach.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbmasach.setBounds(10, 11, 60, 15);
        p2.add(lbmasach);

        JLabel lbsoluong = new JLabel("Số lượng");
        lbsoluong.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbsoluong.setBounds(10, 57, 60, 15);
        p2.add(lbsoluong);

        tfmasach = new JTextField();
        tfmasach.setEditable(false);
        tfmasach.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        tfmasach.setBounds(70, 9, 107, 20);
        p2.add(tfmasach);

        tfsoluong = new JTextField();
        tfsoluong.setColumns(10);
        tfsoluong.setBounds(70, 55, 107, 20);
        p2.add(tfsoluong);

        JButton bThem = new JButton("Thêm");
        bThem.setBounds(11, 164, 138, 38);
        bThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bThem.setContentAreaFilled(false);
        getContentPane().add(bThem);

        JButton bChon = new JButton("Chọn");
        bChon.setBounds(156, 164, 138, 38);
        bChon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bChon.setContentAreaFilled(false);
        getContentPane().add(bChon);

        JButton bSua = new JButton("Sửa");
        bSua.setBounds(483, 296, 200, 38);
        bSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bSua.setContentAreaFilled(false);
        getContentPane().add(bSua);

        bTinhTien = new JButton("Thanh toán: " + tongTien);
        bTinhTien.setBounds(483, 339, 200, 38);
        bTinhTien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bTinhTien.setContentAreaFilled(false);
        bTinhTien.setHorizontalTextPosition(SwingConstants.LEFT);
        getContentPane().add(bTinhTien);

        tfMaSach.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            Statement statement = ketnoi.ConnectDB.getConnection().createStatement();
                            String sql = "SELECT MASACH FROM SACH where MASACH like '" + tfMaSach.getText() + "%'";
                            ResultSet rs = statement.executeQuery(sql);
                            lbTenSach.setText("");
                            lbTenTacGia.setText("");
                            int n = tfMaSach.getText().length();

                            if (n > 13) {
                                modelList.removeAllElements();
                                JOptionPane.showMessageDialog(null, "Mã sách dài hơn quy định!", "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            if (n >= 1) {
                                modelList.removeAllElements();
                                while (rs.next()) {
                                    modelList.addElement(rs.getString("MASACH"));
                                }
                            }

                            if (modelList.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Mã sách không tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                // TODO Auto-generated method stub

            }
        });

        bChon.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                tfMaSach.setText(String.valueOf(list.getSelectedValue()));
                modelList.clear();
                tfSoLuong.requestFocus();
                Statement statement;
                try {
                    statement = ketnoi.ConnectDB.getConnection().createStatement();
                    String sql1 = String.format("SELECT SOLUONGCON FROM KHO WHERE MASACH = '%s'", tfMaSach.getText());
                    ResultSet rs1 = statement.executeQuery(sql1);
                    int sl = rs1.next() ? rs1.getInt("SOLUONGCON") : 0;

                    if (sl == 0) {
                        JOptionPane.showMessageDialog(null, "Sách đã hết!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        tfMaSach.setText("");
                        tfMaSach.requestFocus();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {

                try {
                    String masach = String.valueOf(list.getSelectedValue());

                    Statement statement = ketnoi.ConnectDB.getConnection().createStatement();
                    String sql = String.format("SELECT TENSACH,TENTACGIA FROM SACH,TACGIA WHERE SACH.MATACGIA = TACGIA.MATACGIA AND MASACH = '%s'", masach);
                    ResultSet rs = statement.executeQuery(sql);

                    while (rs.next()) {
                        lbTenSach.setText(rs.getString("TENSACH"));
                        lbTenTacGia.setText(rs.getString("TENTACGIA"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        bReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tfTenKhachHang.setText("");
                tfMaSach.setText("");
                tfNgayLap.setText("");
                tfSoLuong.setText("");
                tfmasach.setText("");
                tfsoluong.setText("");
                tfmasach.setEditable(false);
                lbTenSach.setText("");
                lbTenTacGia.setText("");
                modelList.clear();
                tableRecords.removeAllElements();
                table.setModel(modelTable);
                dem = 0;
                tongTien = 0;
                bTinhTien.setText("Thanh toán: " + tongTien);
            }
        });

        bThem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    String masach = tfMaSach.getText();
                    Statement statement = ketnoi.ConnectDB.getConnection().createStatement();
                    String sql = String.format("SELECT TENSACH FROM SACH WHERE MASACH = '%s'", masach);
                    ResultSet rs = statement.executeQuery(sql);
                    String tenSach = rs.next() ? rs.getString("TENSACH") : null;
                    if (ValidateUtil.checkAnyEmpty(tenSach)) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn mã sách", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String sl = tfSoLuong.getText();
                        if (tfSoLuong.getText().equals("")) {
                            sl = "1";
                        }
                        Vector record = new Vector();
                        record.add(tfMaSach.getText());
                        record.add(tenSach);
                        record.add(sl);
                        int index = -1;
                        for (int j = 0; j < tableRecords.size(); j++) {
                            Object target = tableRecords.get(j);
                            if (target instanceof Vector) {
                                if (((Vector<?>) target).get(0).equals(record.get(0))) {
                                    index = j;
                                    break;
                                }
                            }
                        }
                        if (index == -1) {
                            tableRecords.add(record);
                            kiemTraSoLuongToiDa(record);
                            dem = dem + 1;
                        } else {
                            Integer slOld = Integer.valueOf((String) ((Vector<?>) tableRecords.get(index)).get(2));
                            record.set(2, String.valueOf(slOld + Integer.parseInt(sl)));
                            kiemTraSoLuongToiDa(record);
                            tableRecords.set(index, record);
                        }
                        table.setModel(new DefaultTableModel(tableRecords, tableTitle));
                        tfMaSach.setText("");
                        tfSoLuong.setText("");
                        tinhTongTienHoaDon();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        bSua.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int dongDaChon = table.getSelectedRow();
                if (dongDaChon == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Xin vui lòng chọn dòng cần sửa");
                } else {
                    Vector record = new Vector();
                    Vector dataDongDaChon = (Vector) tableRecords.get(dongDaChon);
                    record.add(tfmasach.getText());
                    record.add(dataDongDaChon.get(1).toString());
                    record.add(tfsoluong.getText());
                    kiemTraSoLuongToiDa(record);
                    tfsoluong.setText((String) record.get(2));
                    tableRecords.set(dongDaChon, record);
                    table.setModel(new DefaultTableModel(tableRecords, tableTitle));
                }
                tinhTongTienHoaDon();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                int dongDaChon = table.getSelectedRow();
                if (dongDaChon != -1) {
                    Vector dataDongDaChon = (Vector) tableRecords.get(dongDaChon);
                    String masach = dataDongDaChon.get(0).toString();
                    String soluong = dataDongDaChon.get(2).toString();
                    tfmasach.setText(masach);
                    tfsoluong.setText(soluong);
                }
            }
        });

        bXoa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int dongDaChon = table.getSelectedRow();
                if (dongDaChon == -1) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa", "Thông báo", JOptionPane.ERROR_MESSAGE);
                } else {
                    Vector dataDongDaChon = (Vector) tableRecords.get(dongDaChon);
                    if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Lua chon", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        tableRecords.remove(dongDaChon);
                        table.setModel(new DefaultTableModel(tableRecords, tableTitle));
                        dem = dem - 1;
                        tfmasach.setText("");
                        tfsoluong.setText("");
                    }
                }
                tinhTongTienHoaDon();
            }
        });

        bTinhTien.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (ValidateUtil.checkAnyEmpty(tfTenKhachHang.getText())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khách hàng", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (dem < 1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng mua hàng trước khi thanh toán", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                tinhTongTienHoaDon();
                insertHoaDon();
                insertChiTietHoaDon();
                JOptionPane.showMessageDialog(null, "Thanh toán thành công");
                bReset.doClick();
                tongTien = 0;
            }
        });
    }

    private void tinhTongTienHoaDon() {
        tongTien = 0;
        tien = new long[dem];

        for (i = 0; i < dem; i++) {
            Vector duLieu = (Vector) tableRecords.get(i);
            String masach = String.valueOf(duLieu.get(0));
            int soluong = Integer.parseInt((String) duLieu.get(2));

            try {

                Statement statement = ketnoi.ConnectDB.getConnection().createStatement();
                String sql1 = "SELECT * FROM sachkhuyenmai where masach = '" + masach + "'";
                ResultSet rs1 = statement.executeQuery(sql1);

                mucgiamgia = rs1.next() ? rs1.getInt("MUCGIAMGIA") : 0;

                String sql = String.format("SELECT GIABIA FROM THONGTINXUATBAN WHERE MASACH = '%s'", masach);
                ResultSet rs = statement.executeQuery(sql);

                while (rs.next()) {
                    int gia = rs.getInt("GIABIA");
                    tien[i] = (long) soluong * gia * (100 - mucgiamgia) / 100;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < dem; i++) {
            tongTien = tongTien + tien[i];
        }
        bTinhTien.setText("Thanh toán: " + tongTien);
    }

    private void kiemTraSoLuongToiDa(Vector record) {
        try {
            Statement statement = ketnoi.ConnectDB.getConnection().createStatement();
            String sql = String.format("SELECT SOLUONGCON FROM KHO WHERE MASACH = '%s'", record.get(0));
            ResultSet rs1 = statement.executeQuery(sql);
            int sl = rs1.next() ? rs1.getInt("SOLUONGCON") : 0;

            if (sl < Integer.parseInt((String) record.get(2))) {
                JOptionPane.showMessageDialog(null, "Số lượng sách vượt hạn mức. Đã tự động điều chỉnh!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                record.set(2, String.valueOf(sl));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertHoaDon() {
        try {
            maHD = 1;
            String tenKH = tfTenKhachHang.getText();
            String ngay;

            if (tfNgayLap.getText().equals("")) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                ngay = dateFormat.format(date);
            } else {
                String[] date = tfNgayLap.getText().split("/");
                ngay = date[2] + "/" + date[1] + "/" + date[0];
            }

            Statement statement = ketnoi.ConnectDB.getConnection().createStatement();
            String sql = "SELECT MAHOADON FROM HOADON ORDER BY MAHOADON DESC LIMIT 1";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                maHD = rs.getInt("MAHOADON") + 1;
            }

            statement.executeUpdate("INSERT INTO HOADON VALUES(" + maHD + ", N'" + tenKH + "', '" + ngay + "', " + tongTien + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertChiTietHoaDon() {


        for (int i = 0; i < dem; i++) {
            Vector duLieu = (Vector) tableRecords.get(i);
            String masach = String.valueOf(duLieu.get(0));
            int soluong = Integer.valueOf(duLieu.get(2).toString());

            try {

                Statement statement = ketnoi.ConnectDB.getConnection().createStatement();
                String sql1 = "SELECT * FROM sachkhuyenmai where masach = '" + masach + "'";
                ResultSet rs1 = statement.executeQuery(sql1);

                mucgiamgia = rs1.next() ? rs1.getInt("MUCGIAMGIA") : 0;

                String sql2 = String.format("INSERT INTO CHITIETHOADON VALUES('%d','%s','%d','%d')", maHD, masach, soluong, mucgiamgia);
                statement.executeUpdate(sql2);

                String query = String.format("update KHO set SOLUONGCON = SOLUONGCON - %d where MASACH = '%s'", soluong, masach);
                statement.executeUpdate(query);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new HoaDon().setVisible(true);
    }

    public void setSelected(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
