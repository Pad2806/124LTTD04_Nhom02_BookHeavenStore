using BookHeavenStoreAPI.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace BookHeavenStoreAPI.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class ChiTietDonHangController : ControllerBase
    {
        private readonly DefautConnections _db;
        public ChiTietDonHangController(DefautConnections db)
        {
            _db = db;
        }

        [HttpGet]
        public async Task<IActionResult> GetSachDaBan()
        {
            var sdb = await _db.ChiTietDonHang.Where(x => _db.DonHang.Any(mdh => mdh.MaDonHang == x.MaDonHang
                                                                         && mdh.NgayThanhToan.HasValue
                                                                         && mdh.TrangThai == "Đã giao"
                                                                         && mdh.NgayThanhToan.Value.Month == DateTime.Now.Month
                                                                         && mdh.NgayThanhToan.Value.Year == DateTime.Now.Year)).SumAsync(x => x.SoLuong);
            return Ok(sdb);
        }
    }
}
