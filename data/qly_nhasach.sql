-- 
-- Database: `qly_nhasach`
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `chitiethoadon`
-- 

CREATE TABLE `chitiethoadon` (
  `MAHOADON` int(11) NOT NULL default '0',
  `MASACH` char(13) collate utf8_unicode_ci NOT NULL default '',
  `SOLUONG` int(11) NOT NULL,
  `MUCGIAMGIA` int(11) default NULL,
  PRIMARY KEY  (`MAHOADON`,`MASACH`),
  KEY `FK_CHITIETHOADON_MASACH` (`MASACH`)
)DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 
-- Dumping data for table `chitiethoadon`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `hoadon`
-- 

CREATE TABLE `hoadon` (
  `MAHOADON` int(11) NOT NULL default '0',
  `TENKHACHHANG` varchar(50) character set utf8 default NULL,
  `NGAYLAP` date default NULL,
  `TONGTIEN` decimal(10,0) default NULL,
  PRIMARY KEY  (`MAHOADON`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 
-- Dumping data for table `hoadon`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `kho`
-- 

CREATE TABLE `kho` (
  `MASACH` char(13) collate utf8_unicode_ci NOT NULL default '',
  `TONGSOLUONG` int(11) default '0',
  `SOLUONGCON` int(11) default '0',
  PRIMARY KEY  (`MASACH`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 
-- Dumping data for table `kho`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `linhvuc`
-- 

CREATE TABLE `linhvuc` (
  `MALINHVUC` char(2) collate utf8_unicode_ci NOT NULL default '',
  `TENLINHVUC` varchar(30) character set utf8 default NULL,
  PRIMARY KEY  (`MALINHVUC`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 
-- Dumping data for table `linhvuc`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `loaisach`
-- 

CREATE TABLE `loaisach` (
  `MALOAISACH` char(2) collate utf8_unicode_ci NOT NULL default '',
  `TENLOAISACH` varchar(20) character set utf8 default NULL,
  PRIMARY KEY  (`MALOAISACH`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 
-- Dumping data for table `loaisach`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `nhatkinhapsach`
-- 

CREATE TABLE `nhatkinhapsach` (
  `STT` int(11) NOT NULL auto_increment,
  `MASACH` char(13) collate utf8_unicode_ci NOT NULL,
  `SOLUONG` int(11) NOT NULL,
  `NGAYNHAP` date default NULL,
  PRIMARY KEY  (`STT`),
  KEY `FK_NHATKINHAPSACH_MASACH` (`MASACH`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- 
-- Dumping data for table `nhatkinhapsach`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `sach`
-- 

CREATE TABLE `sach` (
  `MASACH` char(13) collate utf8_unicode_ci NOT NULL default '',
  `TENSACH` varchar(100) character set utf8 NOT NULL,
  `MATACGIA` char(4) collate utf8_unicode_ci NOT NULL,
  `MALOAISACH` char(2) collate utf8_unicode_ci NOT NULL,
  `MALINHVUC` char(4) collate utf8_unicode_ci NOT NULL,
  `GIAMUA` int(11) NOT NULL,
  PRIMARY KEY  (`MASACH`),
  KEY `FK_SACH_MATACGIA` (`MATACGIA`),
  KEY `FK_SACH_MALOAISACH` (`MALOAISACH`),
  KEY `FK_SACH_MALINHVUC` (`MALINHVUC`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 
-- Dumping data for table `sach`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `sachkhuyenmai`
-- 

CREATE TABLE `sachkhuyenmai` (
  `MASACH` char(13) collate utf8_unicode_ci NOT NULL default '',
  `MUCGIAMGIA` int(11) NOT NULL,
  PRIMARY KEY  (`MASACH`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 
-- Dumping data for table `sachkhuyenmai`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `tacgia`
-- 

CREATE TABLE `tacgia` (
  `MATACGIA` char(4) collate utf8_unicode_ci NOT NULL default '',
  `TENTACGIA` varchar(40) character set utf8 NOT NULL,
  `NAMSINH` char(4) collate utf8_unicode_ci default NULL,
  `NAMMAT` char(4) collate utf8_unicode_ci default NULL,
  `QUEQUAN` varchar(20) character set utf8 default NULL,
  PRIMARY KEY  (`MATACGIA`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 
-- Dumping data for table `tacgia`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `taikhoan`
-- 

CREATE TABLE `taikhoan` (
  `USERNAME` varchar(20) collate utf8_unicode_ci NOT NULL default '',
  `PASSWORD` varchar(100) collate utf8_unicode_ci NOT NULL,
  `TEN` varchar(50) character set utf8 NOT NULL,
  `ID` char(8) collate utf8_unicode_ci NOT NULL,
  `NGAYLAMVIEC` date default NULL,
  `CHUCVU` varchar(50) character set utf8 default NULL,
  PRIMARY KEY  (`USERNAME`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 
-- Dumping data for table `taikhoan`
-- 

INSERT INTO `taikhoan` VALUES ('admin', 'admin', 'asdad', '123213', '2013-12-19', 'aaa');

-- --------------------------------------------------------

-- 
-- Table structure for table `thongtinxuatban`
-- 

CREATE TABLE `thongtinxuatban` (
  `MASACH` char(13) collate utf8_unicode_ci NOT NULL default '',
  `LANTAIBAN` char(11) collate utf8_unicode_ci default NULL,
  `NAMXUATBAN` char(4) collate utf8_unicode_ci default NULL,
  `NHAXUATBAN` varchar(50) character set utf8 default NULL,
  `GIABIA` int(11) NOT NULL,
  PRIMARY KEY  (`MASACH`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 
-- Dumping data for table `thongtinxuatban`
-- 

