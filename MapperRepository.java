@Repository
public interface MapperRepository extends JpaRepository<Mapper, Long> {
    Optional<Mapper> findByVpa(String vpa);
    Optional<Mapper> findByUser(User user);
    Optional<Mapper> findByBankAccount(BankAccount bankAccount);
}
