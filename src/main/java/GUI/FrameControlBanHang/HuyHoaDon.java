package GUI.FrameControlBanHang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


public class HuyHoaDon extends JFrame {

    private JTable table;
    int mhd;

    public HuyHoaDon() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(507, 254);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.FrameControlCongcu.TimMaSach.class.getResource("/data/img/delete.png")));
        setTitle("Kiểm tra hóa đơn");
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 481, 165);
        getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JButton bXoa = new JButton("Trả Hàng & Hoàn Tền");
        bXoa.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        bXoa.setBounds(265, 178, 220, 30);
        bXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bXoa.setContentAreaFilled(false);
        getContentPane().add(bXoa);

        JButton bKiemTra = new JButton("Xem hóa đơn");
        bKiemTra.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        bKiemTra.setBounds(10, 178, 220, 30);
        bKiemTra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bKiemTra.setContentAreaFilled(false);
        getContentPane().add(bKiemTra);

        LoadTable();

        bKiemTra.addActionListener(e -> {
            int index = table.getSelectedRow();
            int maHoaDon = Integer.parseInt(table.getValueAt(index, 0).toString());
            ThongTinHoaDon tinHoaDon = new ThongTinHoaDon(maHoaDon);
            tinHoaDon.setVisible(true);
            tinHoaDon.setFocusable(true);
        });

        bXoa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                int index = table.getSelectedRow();

                if (index == -1) {
                    JOptionPane.showMessageDialog(null, "Xin vui lòng chọn dòng cần thực hiện");
                } else {
                    mhd = Integer.parseInt(table.getValueAt(index, 0).toString());
                    capNhatSoLuongKho();
                    DeleteChiTietHoaDon();
                    DeleteHoaDon();
                    LoadTable();
                }
            }
        });
    }

    private void capNhatSoLuongKho() {
        try {
            Statement statement = ketnoi.ConnectDB.getConnection().createStatement();
            String sql = String.format("select masach, soluong FROM CHITIETHOADON WHERE MAHOADON = '%d'", mhd);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String maSach = resultSet.getString("masach");
                Integer soLuong = resultSet.getInt("soluong");
                Statement statement1 = ketnoi.ConnectDB.getConnection().createStatement();
                String sqlUpdate = String.format("update kho set soluongcon = soluongcon + %d WHERE masach = '%s'", soLuong, maSach);
                statement1.executeUpdate(sqlUpdate);
            }
            JOptionPane.showMessageDialog(null, "Đã cập nhật số lượng sách trong kho");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadTable() {
        try {

            Statement statement = ketnoi.ConnectDB.getConnection().createStatement();
            String sql = "SELECT * FROM HOADON";
            ResultSet rs = statement.executeQuery(sql);
            Vector col = new Vector();
            Vector data = new Vector();
            col.add("Mã hóa đơn");
            col.add("Tên khách hàng");
            col.add("Ngày lập");
            col.add("Tổng tiền");


            while (rs.next()) {
                Vector row = new Vector();
                for (int i = 1; i <= 4; i++) {

                    if (i == 3) {
                        String ngay = rs.getString(i);
                        String[] date = ngay.split("-");
                        String ng = date[2] + "-" + date[1] + "-" + date[0];
                        row.add(ng);
                    } else {
                        row.add(rs.getString(i));
                    }

                }
                data.add(row);
            }
            table.setModel(new DefaultTableModel(data, col));
            table.getColumnModel().getColumn(1).setPreferredWidth(150);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteChiTietHoaDon() {
        try {
            Statement statement = ketnoi.ConnectDB.getConnection().createStatement();
            String sql = String.format("DELETE FROM CHITIETHOADON WHERE MAHOADON = '%d'", mhd);
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteHoaDon() {
        try {

            Statement statement = ketnoi.ConnectDB.getConnection().createStatement();
            String sqlSelect = String.format("select tongtien FROM HOADON WHERE MAHOADON = '%d'", mhd);
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            int tongtien = resultSet.next() ? resultSet.getInt("tongtien") : 0;
            String sql = String.format("DELETE FROM HOADON WHERE MAHOADON = '%d'", mhd);
            statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Đã hủy hóa đơn\nSố tiền hoàn lại cho khách: " + tongtien);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new HuyHoaDon().setVisible(true);
    }

    public void setSelected(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

