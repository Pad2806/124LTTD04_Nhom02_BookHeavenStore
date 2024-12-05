using BookHeavenStoreAPI.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace BookHeavenStoreAPI.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class TacGiaController : ControllerBase
    {
        private readonly DefautConnections _db;

        public TacGiaController(DefautConnections db)
        {
            _db = db;
        }

        [HttpGet]
        public async Task<IActionResult> GetTacGias()
        {
            var tg = await _db.TacGia.ToListAsync();
            return Ok(tg);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetTacGiaById(string id)
        {
            var tg = await _db.TacGia.FindAsync(id);
            if (tg == null)
            {
                return NotFound("Không tìm thấy tác giả");
            }
            return Ok(tg);
        }

        [HttpPost]
        public async Task<IActionResult> PostTacGia(TacGia tacGia)
        {
            int nextId = 0;
            var lastTacGia = await _db.TacGia.OrderByDescending(x => x.MaTacGia).FirstOrDefaultAsync();
            if (lastTacGia != null)
            {
                nextId = int.Parse(lastTacGia.MaTacGia.Substring(2));
                nextId++;
            }
            tacGia.MaTacGia = $"TG{nextId:00000000}";

            _db.Add(tacGia);
            await _db.SaveChangesAsync();
            return Ok(tacGia);
        }

        [HttpPut]
        public async Task<IActionResult> PutTacGia([FromBody]TacGia tacGia)
        {
            var tg = await _db.TacGia.FindAsync(tacGia.MaTacGia);
            if (tg == null)
                return NotFound("Không tìm thấy tác giả");
            tg.TenTacGia = tacGia.TenTacGia;
            tg.SoLuongTP = tacGia.SoLuongTP;
            tg.HinhAnh = tacGia.HinhAnh;

            _db.TacGia.Update(tg);
            await _db.SaveChangesAsync();
            return Ok(tg);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTacGia(string id)
        {
            var tg = await _db.TacGia.FindAsync(id);
            if (tg == null)
                return NotFound("Không tìm thấy tác giả");
            _db.TacGia.Remove(tg);
            await _db.SaveChangesAsync();
            return Ok("Đã xoá thành công");
        }
    }
}
