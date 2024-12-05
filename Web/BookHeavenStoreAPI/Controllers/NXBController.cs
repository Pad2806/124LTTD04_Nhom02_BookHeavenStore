using BookHeavenStoreAPI.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace BookHeavenStoreAPI.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class NXBController : ControllerBase
    {
        private readonly DefautConnections _db;
        public NXBController(DefautConnections db)
        {
            _db = db;
        }

        [HttpGet] 
        public async Task<IActionResult> GetNXBs()
        {
            var nxb = await _db.NXB.ToListAsync();
            return Ok(nxb);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetNXBById(string id)
        {
            var nxb = await _db.NXB.FindAsync(id);
            if (nxb == null)
                return NotFound("Không tìm thấy nhà xuất bản");
            return Ok(nxb);
        }

        [HttpPost]
        public async Task<IActionResult> PostNXB(NXB nxb)
        {
            if (nxb.MaNXB != null)
                nxb.MaNXB = nxb.MaNXB.ToUpper();
            else
                return BadRequest("Mã nhà xuất bản không được trống");
            _db.NXB.Add(nxb);
            await _db.SaveChangesAsync();
            return Ok(nxb);
        }

        [HttpPut]
        public async Task<IActionResult> PutNXB([FromBody] NXB nxb)
        {
            var n = await _db.NXB.FindAsync(nxb.MaNXB);
            if (n == null)
                return NotFound("Không tìm thấy nhà xuất bản");
            n.MaNXB = nxb.MaNXB.ToUpper();
            n.TenNXB = nxb.TenNXB;
            n.DiaChi = nxb.DiaChi;
            n.Email = nxb.Email;

            _db.NXB.Update(n);
            await _db.SaveChangesAsync();
            return Ok(n);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteNXB(string id)
        {
            var nxb = await _db.NXB.FindAsync(id);
            if (nxb == null) return NotFound("Không tìm thấy nhà xuất bản");

            _db.NXB.Remove(nxb);
            await _db.SaveChangesAsync();
            return Ok("Đã xoá thành công");
        }
    }
}
