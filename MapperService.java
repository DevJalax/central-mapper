@Service
public class MapperService {
    private final MapperRepository mapperRepository;

    public MapperService(MapperRepository mapperRepository) {
        this.mapperRepository = mapperRepository;
    }

    public void createVPA(User user, String vpa) {
        Mapper mapper = new Mapper();
        mapper.setUser(user);
        mapper.setVpa(vpa);
        mapperRepository.save(mapper);
    }

    public void linkBankAccount(User user, BankAccount bankAccount) {
        Mapper mapper = mapperRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Mapper not found for user"));
        mapper.setBankAccount(bankAccount);
        mapperRepository.save(mapper);
    }

    public User getUserByVPA(String vpa) {
        return mapperRepository.findByVpa(vpa)
                .map(Mapper::getUser)
                .orElseThrow(() -> new RuntimeException("VPA not found"));
    }

    public BankAccount getBankAccountByUser(User user) {
        return mapperRepository.findByUser(user)
                .map(Mapper::getBankAccount)
                .orElseThrow(() -> new RuntimeException("Bank account not found for user"));
    }
}
