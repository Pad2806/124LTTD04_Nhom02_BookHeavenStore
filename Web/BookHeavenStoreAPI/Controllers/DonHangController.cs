using BookHeavenStoreAPI.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace BookHeavenStoreAPI.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class DonHangController : ControllerBase
    {
        private readonly DefautConnections _db;
        public DonHangController(DefautConnections db)
        {
            _db = db;
        }

        [HttpGet]
        public async Task<IActionResult> GetDonHangs()
        {
            var dh = await _db.DonHang.ToListAsync();
            return Ok(dh);
        }
        [HttpGet]
        public async Task<IActionResult> GetDoanhThuTrongThang()
        {
            var dt = await _db.DonHang.Where(x => x.NgayThanhToan.HasValue
                && x.TrangThai == "Đã giao"
                && x.NgayThanhToan.Value.Month == DateTime.Now.Month
                && x.NgayThanhToan.Value.Year == DateTime.Now.Year).SumAsync(x => x.TongTien);
            return Ok(dt);
        }
        [HttpGet]
        public async Task<IActionResult> GetSoDon()
        {
            var sd = await _db.DonHang.Where(x => x.NgayThanhToan.HasValue
                && x.TrangThai == "Đã giao"
                && x.NgayThanhToan.Value.Month == DateTime.Now.Month
                && x.NgayThanhToan.Value.Year == DateTime.Now.Year).CountAsync();
            return Ok(sd);
        }

        [HttpGet]
        public async Task<IActionResult> GetDoanhThuTrongNam()
        {
            var dttn = await _db.DonHang.Where(x => x.NgayThanhToan.HasValue 
                && x.NgayThanhToan.Value.Year == DateTime.Now.Year 
                && x.TrangThai == "Đã giao")
            .GroupBy(x => new { x.NgayThanhToan.Value.Month, x.NgayThanhToan.Value.Year })
            .Select(g => new
            {
                Month = g.Key.Month,
                Year = g.Key.Year,
                Total = g.Sum(x => x.TongTien)
            })
            .OrderBy(x => x.Year)
            .ThenBy(x => x.Month)
            .ToListAsync();
            return Ok(dttn);
        }
        [HttpGet("{id}")]
        public async Task<IActionResult> GetDonHangById(string id)
        {
            var dh = await _db.DonHang.FindAsync(id);
            if(dh == null)
                return NotFound("Không tìm thấy đơn hàng");
            return Ok(dh);
        }

        [HttpPost]
        public async Task<IActionResult> PostDonHang(DonHang donHang)
        {
            int nextId = 0;
            var lastDonHang = await _db.DonHang.OrderByDescending(x=>x.MaDonHang).FirstOrDefaultAsync();
            if(lastDonHang != null)
            {
                nextId = int.Parse(lastDonHang.MaDonHang.Substring(2));
                nextId++;
            }
            donHang.MaDonHang = $"DH{nextId:00000000}";

            _db.DonHang.Add(donHang);
            await _db.SaveChangesAsync();
            return Ok(donHang);
        }

        [HttpPut]
        public async Task<IActionResult> PutDonHang([FromBody] DonHang donHang)
        {
            var dh = await _db.DonHang.FindAsync(donHang.MaDonHang);
            if (dh == null)
                return NotFound("Không tìm thấy đơn hàng");
            dh.GiamGia = donHang.GiamGia;
            dh.TrangThai = donHang.TrangThai;
            dh.NgayGiaoHang = donHang.NgayGiaoHang;
            dh.NgayThanhToan = donHang.NgayThanhToan;

            _db.DonHang.Update(dh);
            await _db.SaveChangesAsync();
            return Ok(dh);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteDonHang(string id)
        {
            var dh = await _db.DonHang.FindAsync(id);
            if (dh == null)
                return NotFound("Không tìm thấy đơn hàng");

            _db.DonHang.Remove(dh);
            await _db.SaveChangesAsync();
            return Ok("Đã xoá thành công");
        }
    }
}
