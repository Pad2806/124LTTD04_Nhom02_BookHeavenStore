using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace BookHeavenStoreMVC.Controllers
{
    public class TrangChuController : Controller
    {
        Uri baseAddress = new Uri("https://localhost:44388/api");
        private readonly HttpClient _client;
        public TrangChuController()
        {
            _client = new HttpClient();
            _client.BaseAddress = baseAddress;
        }
        [HttpGet]
        public IActionResult TrangChuView()
        {
            int? doanhThuTheoThang = null;
            int? sachDaBan = null;
            int? soLuongDonHang = null;
            
            //Doanh thu theo tháng
            try
            {
                HttpResponseMessage response = _client.GetAsync(_client.BaseAddress + "/DonHang/GetDoanhThuTrongThang").Result;
                if (response.IsSuccessStatusCode)
                {
                    string data = response.Content.ReadAsStringAsync().Result;
                    doanhThuTheoThang = JsonConvert.DeserializeObject<int?>(data);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            ViewBag.doanhThuTheoThang = doanhThuTheoThang;

            //Sách đã bán trong tháng
            try
            {
                HttpResponseMessage response = _client.GetAsync(_client.BaseAddress + "/ChiTietDonHang/GetSachDaBan").Result;
                if (response.IsSuccessStatusCode)
                {
                    string data = response.Content.ReadAsStringAsync().Result;
                    sachDaBan = JsonConvert.DeserializeObject<int?>(data);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            ViewBag.sachDaBan = sachDaBan;

            //Số lượng đơn hàng trong tháng
            try
            {
                HttpResponseMessage response = _client.GetAsync(_client.BaseAddress + "/DonHang/GetSoDon").Result;
                if (response.IsSuccessStatusCode)
                {
                    string data = response.Content.ReadAsStringAsync().Result;
                    soLuongDonHang = JsonConvert.DeserializeObject<int?>(data);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            ViewBag.soLuongDonHang = soLuongDonHang;
            //Doanh thu trong năm
            List<dynamic> doanhThuTrongNam = null;

            try
            {
                HttpResponseMessage response = _client.GetAsync(_client.BaseAddress + "/DonHang/GetDoanhThuTrongNam").Result;
                if (response.IsSuccessStatusCode)
                {
                    var jsonResponse = response.Content.ReadAsStringAsync().Result;
                    doanhThuTrongNam = JsonConvert.DeserializeObject<List<dynamic>>(jsonResponse);
                    ViewBag.DoanhThuTrongNam = doanhThuTrongNam;
                }
                else
                {
                    Console.WriteLine($"Lỗi từ API: {response.StatusCode} - {response.ReasonPhrase}");
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Lỗi lấy doanh thu trong năm: " + ex.Message);
            }

            return View(doanhThuTrongNam);
        }
    }
}
