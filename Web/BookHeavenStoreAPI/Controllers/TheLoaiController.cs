using BookHeavenStoreAPI.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace BookHeavenStoreAPI.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class TheLoaiController : ControllerBase
    {
        private readonly DefautConnections _db;
        public TheLoaiController(DefautConnections db)
        {
            _db = db;
        }

        [HttpGet]
        public async Task<IActionResult> GetTheLoais()
        {
            var tl = await _db.TheLoai.ToListAsync();
            return Ok(tl);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetTheLoaiById(string id)
        {
            var tl = await _db.TheLoai.FindAsync(id);
            if (tl == null)
                return NotFound("Không tìm thấy thể loại");
            return Ok(tl);
        }

        [HttpPost]
        public async Task<IActionResult> PostTheLoai(TheLoai theLoai)
        {
            if (theLoai.MaTheLoai != null)
            {
                theLoai.MaTheLoai = theLoai.MaTheLoai.ToUpper();
            }
            _db.TheLoai.Add(theLoai);
            await _db.SaveChangesAsync();
            return Ok(theLoai);
        }

        [HttpPut]
        public async Task<IActionResult> PutTheLoai([FromBody] TheLoai theLoai)
        {
            var tl = await _db.TheLoai.FindAsync(theLoai.MaTheLoai);
            if (tl == null)
                return NotFound("Không tìm thấy thể loại");

            tl.MaTheLoai = theLoai.MaTheLoai.ToUpper();
            tl.TenTheLoai = theLoai.TenTheLoai;
            tl.HinhAnh = theLoai.HinhAnh;
            _db.TheLoai.Update(tl);
            await _db.SaveChangesAsync();
            return Ok(theLoai);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTheLoai(string id)
        {
            var tl = await _db.TheLoai.FindAsync(id);
            if (tl == null)
                return NotFound("Không tìm thấy thể loại");
            _db.TheLoai.Remove(tl);
            await _db.SaveChangesAsync();
            return Ok("Đã xoá thành công");
        }
    }
}
