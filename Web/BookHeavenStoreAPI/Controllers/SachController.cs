using BookHeavenStoreAPI.Models;
using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace BookHeavenStoreAPI.Controllers
{
    [ApiController]
    [Route("api/sach")]
    public class SachController : ControllerBase
    {
        private readonly DefautConnections _db;

        public SachController(DefautConnections db)
        {
            _db = db;
        }

        [HttpGet]
        public async Task<IActionResult> GetSachs()
        {
            var sach = await _db.Sach.ToListAsync();
            return Ok(sach);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetSachById(string id)
        {
            var sach = await _db.Sach.FindAsync(id);
            if (sach == null)
            {
                return NotFound("Không tìm thấy sách");
            }
            return Ok(sach);
        }

        [HttpPost]
        public async Task<IActionResult> PostSach(Sach sach)
        {
            sach.MaSach = await GenerateMaSach();
            if (string.IsNullOrEmpty(sach.MaTacGia) || !_db.TacGia.Any(g => g.MaTacGia == sach.MaTacGia))
            {
                sach.MaTacGia = await GenerateMaTacGia();
                var tacgia = new TacGia
                {
                    MaTacGia = sach.MaTacGia,
                };
                _db.TacGia.Add(tacgia);
                await _db.SaveChangesAsync();
            }
            _db.Sach.Add(sach);
            await _db.SaveChangesAsync();
            return Ok(sach);
        }
        [NonAction]
        private async Task<string> GenerateMaTacGia()
        {
            var lastTacGia = await _db.TacGia
                .OrderByDescending(x => x.MaTacGia)
                .FirstOrDefaultAsync();

            int nextId = 0;
            if (lastTacGia != null)
            {
                nextId = int.Parse(lastTacGia.MaTacGia.Substring(2));
                nextId++;
            }

            return $"TG{nextId:00000000}";
        }
        [NonAction]
        public async Task<string> GenerateMaSach()
        {
            int nextId = 0;
            var lastSach = await _db.Sach
                .OrderByDescending(x => x.MaSach)
                .FirstOrDefaultAsync();
            if (lastSach != null)
            {
                nextId = int.Parse(lastSach.MaSach.Substring(4));
                nextId++;
            }

            return $"BOOK{nextId:000000}";
        }
        [HttpPut]
        public async Task<IActionResult> PutSach([FromBody] Sach sach)
        {
            var s = await _db.Sach.FindAsync(sach.MaSach);

            if (s == null)
                return NotFound("Không tìm thấy sách");
            s.TenSach = sach.TenSach;
            s.MaTheLoai = sach.MaTheLoai;
            s.MaTacGia = sach.MaTacGia;
            s.MaNXB = sach.MaNXB;
            s.DonGiaBan = sach.DonGiaBan;
            s.SoLuongCon = sach.SoLuongCon;
            s.HinhAnh = sach.HinhAnh;
            s.MoTa = sach.MoTa;

            _db.Sach.Update(s);
            await _db.SaveChangesAsync();
            return Ok(sach);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteSach(string id)
        {
            var s = await _db.Sach.FindAsync(id);
            if (s == null)
                return NotFound("Không tìm thấy sách");

            _db.Sach.Remove(s);
            await _db.SaveChangesAsync();
            return Ok("Đã xoá thành công");
        }

    }
}
