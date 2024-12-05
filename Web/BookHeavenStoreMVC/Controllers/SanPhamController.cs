using BookHeavenStoreAPI.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Text;

namespace BookHeavenStoreMVC.Controllers
{
    public class SanPhamController : Controller
    {
        Uri baseAddress = new Uri("https://localhost:44388/api");
        private readonly HttpClient _client;
        public SanPhamController()
        {
            _client = new HttpClient();
            _client.BaseAddress = baseAddress;
        }
        public IActionResult SanPhamView()
        {
            List<Sach> spList = new List<Sach>();
            List<TacGia> tgList = new List<TacGia>();
            List<NXB> nxbList = new List<NXB>();
            List<TheLoai> tlList = new List<TheLoai>();
            HttpResponseMessage spResponse = _client.GetAsync(_client.BaseAddress + "/Sach/GetSachs").Result;
            if (spResponse.IsSuccessStatusCode)
            {
                string data = spResponse.Content.ReadAsStringAsync().Result;
                spList = JsonConvert.DeserializeObject<List<Sach>>(data);
            }

            HttpResponseMessage tgResponse = _client.GetAsync(_client.BaseAddress + "/Tacgia/GetTacGias").Result;
            if (tgResponse.IsSuccessStatusCode)
            {
                string data = tgResponse.Content.ReadAsStringAsync().Result;
                tgList = JsonConvert.DeserializeObject<List<TacGia>>(data);
            }

            HttpResponseMessage nxbResponse = _client.GetAsync(_client.BaseAddress + "/NXB/GetNXBs").Result;
            if (nxbResponse.IsSuccessStatusCode)
            {
                string data = nxbResponse.Content.ReadAsStringAsync().Result;
                nxbList = JsonConvert.DeserializeObject<List<NXB>>(data);
            }

            HttpResponseMessage tlResponse = _client.GetAsync(_client.BaseAddress + "/TheLoai/GetTheLoais").Result;
            if (tlResponse.IsSuccessStatusCode)
            {
                string data = tlResponse.Content.ReadAsStringAsync().Result;
                tlList = JsonConvert.DeserializeObject<List<TheLoai>>(data);
            }

            var model = new Tuple<IEnumerable<Sach>, IEnumerable<TacGia>, IEnumerable<TheLoai>, IEnumerable<NXB>>(spList, tgList, tlList, nxbList);
            
            return View(model);
        }

        
        public IActionResult XoaSp(string id)
        {
            HttpResponseMessage response = _client.GetAsync(_client.BaseAddress + "/Sach/GetSachById/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                string data = response.Content.ReadAsStringAsync().Result;
                Sach sach = JsonConvert.DeserializeObject<Sach>(data);
                if (sach != null)
                {
                    HttpContent content = new StringContent(JsonConvert.SerializeObject(sach), Encoding.UTF8, "application/json");
                    HttpResponseMessage deleteResponse = _client.DeleteAsync(_client.BaseAddress + "/Sach/DeleteSach/" + id).Result;

                    if (deleteResponse.IsSuccessStatusCode)
                    {
                        TempData["successMessage"] = "Đã xoá sản phẩm thành công";
                        return RedirectToAction("SanPhamView");
                    }
                }
            }
            return RedirectToAction("SanPhamView");
        }
    }
}
