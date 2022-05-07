# TourismManagement
Website quản lý du lịch online ứng dụng của môn `Lập trình Java`

# Mô tả chung
- Mô hình: `MVC`
- Mẫu thiết kế: `Front-Controller Design Pattern`
- Ngôn ngữ: `Java`, `Javascript (Jquery)`, `HTML`, `CSS`, `XML`
- Framework, API, Library, Service: `Spring MVC`, `Hibernate`, `Bootstrap 4`, `Cloudinary`, `ChartJs`, `JsPDF`, `Twillio`, `CKEditor`, `Province-Open`, `Sweet Alert`, `Messenger Plugin`, `moment.js`
- Database: `Mysql`
- Nền tảng thanh toán online: `Momo`
- IDE, Công cụ: `Intellij IDE`, `Vscode`, `MySQL Workbench`,`Git/GitHub`, `Trello`, `Diagrams.net`

# Chức năng (Có phân quyền người dùng, có phân trang dữ liệu)
- Đăng nhập
- Đăng ký 
- Đăng xuất
- Quản trị viên
    + Quản lý tài khoản
    + Quản lý tin tức
    + Quản lý tour du lịch
    + Quản lý vai trò tài khoản
    + Quản lý hóa đơn
    + Quản lý hình thức thanh toán
    + Quản lý kho chứa
    + Quản lý danh mục 
    + Quản lý khuyến mại
    + Quản lý phàn trăm khuyến mại
    + Quản lý ngày khởi hành
    + Quản lý đặc điểm
    + Quản lý ghi chú
    + Quản lý lịch trình
    + Quản lý dịch vụ
    + Quản lý đối tượng phục vụ
    + Quản lý hình thu nhỏ
    + Quản lý phương tiện di chuyển
    + Thống kê (doanh thu, số lượng tour theo tháng, quý ,năm, giữa 2 tháng, giữa 2 quý, giữa 2 năm)
    + Báo cáo (doanh thu, số lượng tour theo tháng, quý, năm)
- Nhân viên
    + Đặt tour
    + Thanh toán trực tiếp
- Khách hàng
    + Xem danh sách tour, tin tức
    + Tìm kiếm tour (giá, tên, lịch trình)
    + Xem chi tiết tour, tin tức
    + Bình luận, đánh giá tour
    + Bình luận, like tin tức
    + Đặt tour

- Các tính năng thêm
    + Tích hợp hệ thống chat bot tự động bằng Messenger vào website 
    + Xác thực bằng OTP thông qua gmail khi đăng kí tài khoản 
    + Tích hợp Momo khi tiến hành thanh toán
    + Tích hợp gửi tin nhắn SMS thông qua Twillio API khi khách hàng không sử dụng hình thức thanh toán trực tuyến Momo
    + Sử dụng Cloudinary service để lưu trữ hình ảnh của website
    + Tích hợp trình soạn thảo văn bản trực tiếp CKEditor vào website 
    + Sử dụng CharJs để hiển thị biểu đồ thống kê
    + Sử dụng JsPdf để xuất dữ liệu ra thành file pdf
    + Ngoài ra còn sử dụng một số tính năng khác: Sử dụng Province-Open API để lấy thông tin các tỉnh thành/quận huyện ở Việt Nam, Sweet Alert để hiển thị các thông báo, Moment.js để tính thời gian 