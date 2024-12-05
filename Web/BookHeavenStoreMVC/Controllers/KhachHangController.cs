using BookHeavenStoreAPI;
using BookHeavenStoreMVC.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Text;
using System.Text.Json.Serialization;

namespace BookHeavenStoreMVC.Controllers
{
    public class KhachHangController : Controller
    {
        Uri baseAddress = new Uri("https://localhost:44388/api");
        private readonly HttpClient _client;
        public KhachHangController()
        {
            _client = new HttpClient();
            _client.BaseAddress = baseAddress;
        }
        [HttpGet]
        public IActionResult KhachHangView()
        {
            List<KhachHang> khList = new List<KhachHang>();
            HttpResponseMessage response = _client.GetAsync(_client.BaseAddress + "/KhachHang/GetKhachHangs").Result;
            if (response.IsSuccessStatusCode)
            {
                string data = response.Content.ReadAsStringAsync().Result;
                khList = JsonConvert.DeserializeObject<List<KhachHang>>(data);
            }
            return View(khList);
        }
        public IActionResult KhoaKH(string id)
        {
            HttpResponseMessage response = _client.GetAsync(_client.BaseAddress + "/KhachHang/GetKhachHangById/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                string data = response.Content.ReadAsStringAsync().Result;
                KhachHang khachHang = JsonConvert.DeserializeObject<KhachHang>(data);

                if (khachHang != null)
                {
                    khachHang.TrangThai = !khachHang.TrangThai;
                    HttpContent content = new StringContent(JsonConvert.SerializeObject(khachHang), Encoding.UTF8, "application/json");
                    HttpResponseMessage updateResponse = _client.PutAsync(_client.BaseAddress + "/KhachHang/PutKhachHang", content).Result;

                    if (updateResponse.IsSuccessStatusCode)
                    {
                        TempData["successMessage"] = "Đã khoá tài khoản";
                        return RedirectToAction("KhachHangView");
                    }
                }
            }
            return RedirectToAction("KhachHangView");
        }
    }
}
