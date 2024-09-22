@RestController
@RequestMapping("/mappers")
public class MapperController {
    private final MapperService mapperService;

    public MapperController(MapperService mapperService) {
        this.mapperService = mapperService;
    }

    @PostMapping("/vpa")
    public ResponseEntity<Void> createVPA(@RequestBody CreateVPARequest request) {
        User user = userService.getUser(request.getUserId());
        mapperService.createVPA(user, request.getVpa());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/link-account")
    public ResponseEntity<Void> linkBankAccount(@RequestBody LinkAccountRequest request) {
        User user = userService.getUser(request.getUserId());
        BankAccount bankAccount = bankAccountService.getBankAccount(request.getBankAccountId());
        mapperService.linkBankAccount(user, bankAccount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user-by-vpa/{vpa}")
    public ResponseEntity<User> getUserByVPA(@PathVariable String vpa) {
        User user = mapperService.getUserByVPA(vpa);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/bank-account-by-user/{userId}")
    public ResponseEntity<BankAccount> getBankAccountByUser(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        BankAccount bankAccount = mapperService.getBankAccountByUser(user);
        return ResponseEntity.ok(bankAccount);
    }
}
