using BookHeavenStoreAPI.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace BookHeavenStoreAPI.Controllers
{
    [ApiController]
    [Route("api/khachhang")]
    public class KhachHangController : ControllerBase
    {
        private readonly DefautConnections _db;
        public KhachHangController(DefautConnections db)
        {
            _db = db;
        }

        [HttpGet]
        public async Task<IActionResult> GetKhachHangs()
        {
            var kh = await _db.KhachHang.ToListAsync();
            return Ok(kh);
        }

        [HttpGet]
        [Route("{id}")]
        public async Task<IActionResult> GetKhachHangById(string id)
        {
            var khachHang = await _db.KhachHang.FindAsync(id);
            if (khachHang == null)
            {
                return NotFound("Không tìm thấy khách hàng");
            }
            return Ok(khachHang);
        }
        [HttpPost]
        public async Task<IActionResult> PostKhachHang(KhachHang khachHang)
        {
            khachHang.MaKhachHang = await GenerateMaKhachHang();
            if (string.IsNullOrEmpty(khachHang.MaGioHang) || !_db.GioHang.Any(g => g.MaGioHang == khachHang.MaGioHang))
            {
                khachHang.MaGioHang = await GenerateMaGioHang();
                var gioHang = new GioHang
                {
                    MaGioHang = khachHang.MaGioHang,
                };
                _db.GioHang.Add(gioHang);
                await _db.SaveChangesAsync();
            }
            _db.KhachHang.Add(khachHang);
            await _db.SaveChangesAsync();
            //return CreatedAtAction(nameof(GetKhachHangById), new { id = khachHang.MaKhachHang }, khachHang);
            return Ok(khachHang);
        }
        [NonAction]
        private async Task<string> GenerateMaKhachHang()
        {
            var lastKhachHang = await _db.KhachHang
                .OrderByDescending(k => k.MaKhachHang)
                .FirstOrDefaultAsync();

            int nextId = int.Parse(lastKhachHang.MaKhachHang.Substring(2));
            if (lastKhachHang != null)
            {
                nextId = int.Parse(lastKhachHang.MaKhachHang.Substring(2)) + 1;
            }

            return $"KH{nextId:00000000}";
        }
        [NonAction]
        private async Task<string> GenerateMaGioHang()
        {
            var lastGioHang = await _db.KhachHang
                .OrderByDescending(k => k.MaGioHang)
                .FirstOrDefaultAsync();

            int nextId = int.Parse(lastGioHang.MaGioHang.Substring(2));
            if (lastGioHang != null)
            {
                nextId = int.Parse(lastGioHang.MaGioHang.Substring(2)) + 1;
            }

            return $"GH{nextId:00000000}";
        }

        [HttpPut]
        public async Task<IActionResult> PutKhachHang([FromBody] KhachHang khachHang)
        {
            var kh = await _db.KhachHang.FindAsync(khachHang.MaKhachHang);
            if (kh == null)
            {
                return NotFound("Không tìm thấy khách hàng.");
            }

            // Cập nhật thông tin
            kh.TenKhachHang = khachHang.TenKhachHang;
            kh.HinhAnh = khachHang.HinhAnh;
            kh.DiaChi = khachHang.DiaChi;
            kh.MatKhau = khachHang.MatKhau;
            kh.Email = khachHang.Email;
            kh.SDT = khachHang.SDT;
            kh.NgaySinh = khachHang.NgaySinh;
            kh.HangThanhVien = khachHang.HangThanhVien;
            kh.NgayDangKy = khachHang.NgayDangKy;

            _db.KhachHang.Update(kh);
            await _db.SaveChangesAsync();

            return Ok(khachHang);

        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteKhachHang(string id)
        {
            var khachHang = await _db.KhachHang.FindAsync(id);
            if (khachHang == null)
            {
                return NotFound("Khách hàng không tồn tại");
            }

            _db.KhachHang.Remove(khachHang);
            await _db.SaveChangesAsync();
            return Ok("Đã xoá thành công");
        }
    }
}

