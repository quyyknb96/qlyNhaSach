package GUI.FrameControlBaoCao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ReportHoadon extends JFrame {

    public ReportHoadon(String fileName, Integer maHoaDon) {
        super("Hóa đơn");
        try {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("user", "admin");
            objectMap.put("maHoaDon", maHoaDon);
            Statement statement = ketnoi.ConnectDB.getConnection().createStatement();
            String sql = "SELECT TENKHACHHANG, NGAYLAP, TONGTIEN FROM hoadon where MAHOADON = " + maHoaDon;
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                objectMap.put("TONGTIEN", rs.getString("TONGTIEN"));
//                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                objectMap.put("NGAYMUAHANG", rs.getTimestamp("NGAYLAP"));
                objectMap.put("TENKHACHHANG", rs.getString("TENKHACHHANG"));
            }
            JasperPrint print = JasperFillManager.fillReport(fileName, objectMap, ketnoi.ConnectDB.getConnection());
            JRViewer viewer = new JRViewer(print);

            Container c = getContentPane();
            c.add(viewer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.Login.class.getResource("/data/img/report.png")));
    }

    public static void main(String[] args) throws JRException {
        ReportHoadon viewer = new ReportHoadon("Report/rp_Hoadon.jasper", 5);
        viewer.setVisible(true);
    }

}

