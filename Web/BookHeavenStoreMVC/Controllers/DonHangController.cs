using BookHeavenStoreAPI.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Text;

namespace BookHeavenStoreMVC.Controllers
{
    public class DonHangController : Controller
    {
        Uri baseAddress = new Uri("https://localhost:44388/api");
        private readonly HttpClient _client;
        public DonHangController()
        {
            _client = new HttpClient();
            _client.BaseAddress = baseAddress;
        }
        [HttpGet]
        public IActionResult DonHangView()
        {
            List<DonHang> dhList = new List<DonHang>();
            List<KhachHang> khList = new List<KhachHang>();
            HttpResponseMessage dhResponse = _client.GetAsync(_client.BaseAddress + "/DonHang/GetDonHangs").Result;
            if (dhResponse.IsSuccessStatusCode)
            {
                string data = dhResponse.Content.ReadAsStringAsync().Result;
                dhList = JsonConvert.DeserializeObject<List<DonHang>>(data);
            }
            HttpResponseMessage khResponse = _client.GetAsync(_client.BaseAddress + "/KhachHang/GetKhachHangs").Result;
            if (khResponse.IsSuccessStatusCode)
            {
                string data = khResponse.Content.ReadAsStringAsync().Result;
                khList = JsonConvert.DeserializeObject<List<KhachHang>>(data);
            }
            var model = new Tuple<IEnumerable<DonHang>, IEnumerable<KhachHang>>(dhList, khList);
            return View(model);
        }

        public IActionResult XacNhanDon(string id)
        {
            HttpResponseMessage response = _client.GetAsync(_client.BaseAddress + "/DonHang/GetDonHangById/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                string data = response.Content.ReadAsStringAsync().Result;
                DonHang donHang = JsonConvert.DeserializeObject<DonHang>(data);

                if (donHang != null)
                {
                    donHang.TrangThai = "Đang giao";
                    HttpContent content = new StringContent(JsonConvert.SerializeObject(donHang), Encoding.UTF8, "application/json");
                    HttpResponseMessage updateResponse = _client.PutAsync(_client.BaseAddress + "/DonHang/PutDonHang", content).Result;

                    if (updateResponse.IsSuccessStatusCode)
                    {
                        TempData["successMessage"] = "Đã xác nhận đơn hàng, đơn hàng sẽ được giao đến khách hàng";
                        return RedirectToAction("DonHangView");
                    }
                }
            }
            return RedirectToAction("DonHangView");
        }
        public IActionResult HuyDon(string id)
        {
            HttpResponseMessage response = _client.GetAsync(_client.BaseAddress + "/DonHang/GetDonHangById/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                string data = response.Content.ReadAsStringAsync().Result;
                DonHang donHang = JsonConvert.DeserializeObject<DonHang>(data);

                if (donHang != null)
                {
                    donHang.TrangThai = "Đã huỷ";
                    HttpContent content = new StringContent(JsonConvert.SerializeObject(donHang), Encoding.UTF8, "application/json");
                    HttpResponseMessage updateResponse = _client.PutAsync(_client.BaseAddress + "/DonHang/PutDonHang", content).Result;

                    if (updateResponse.IsSuccessStatusCode)
                    {
                        TempData["successMessage"] = "Đã huỷ đơn hàng";
                        return RedirectToAction("DonHangView");
                    }
                }
            }
            return RedirectToAction("DonHangView");
        }
        public IActionResult GiaoHang(string id)
        {
            HttpResponseMessage response = _client.GetAsync(_client.BaseAddress + "/DonHang/GetDonHangById/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                string data = response.Content.ReadAsStringAsync().Result;
                DonHang donHang = JsonConvert.DeserializeObject<DonHang>(data);

                if (donHang != null)
                {
                    donHang.TrangThai = "Đã giao";
                    //if(donHang.MaPTTT == "TTKNH")
                    //{
                    //    donHang.NgayGiaoHang = 
                    //}
                    HttpContent content = new StringContent(JsonConvert.SerializeObject(donHang), Encoding.UTF8, "application/json");
                    HttpResponseMessage updateResponse = _client.PutAsync(_client.BaseAddress + "/DonHang/PutDonHang", content).Result;

                    if (updateResponse.IsSuccessStatusCode)
                    {
                        TempData["successMessage"] = "Đã giao hàng thành công";
                        return RedirectToAction("DonHangView");
                    }
                }
            }
            return RedirectToAction("DonHangView");
        }
    }
}
