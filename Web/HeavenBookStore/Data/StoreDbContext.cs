using HeavenBookStore.Models;
using Microsoft.EntityFrameworkCore;

namespace HeavenBookStore.Data
{
    public class StoreDbContext : DbContext
    {
        public StoreDbContext(DbContextOptions<StoreDbContext> options) : base(options)
        {
        }
        public DbSet<KhachHang> KhachHangs { get; set; }
    }
}
