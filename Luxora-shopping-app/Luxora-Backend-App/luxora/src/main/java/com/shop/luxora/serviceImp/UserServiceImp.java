package com.shop.luxora.serviceImp;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.luxora.entity.User;
import com.shop.luxora.repository.UserRepository;
import com.shop.luxora.service.UserService;

import jakarta.persistence.EntityNotFoundException;


@Service
public class UserServiceImp implements UserService{
	
	private PasswordEncoder passwordEncoder;
	private UserRepository userRepo;
	private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$";
	private static final String MOBILE_NUMBER_REGEX = "^[6789]\\d{9}$";
	private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,30}$";
	
	

	public UserServiceImp(PasswordEncoder passwordEncoder, UserRepository userRepo) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepo = userRepo;
	}

	@Override
	public List<User> saveAll(List<User> userlist) {
		if(userlist == null || userlist.isEmpty()) {
			throw new IllegalArgumentException("User List can't be null or empty.");
		}
		
		try {
			return userRepo.saveAll(userlist);
		} catch(Exception ex) {
			throw new RuntimeException("Failed to save user list.\n"+ ex.getMessage());
		}
	}

	@Override
	public List<User> findAll() {
		try {
			return userRepo.findAll();
		} catch (Exception ex) {
			throw new RuntimeException("An unexpected error occured.\n"+ex.getLocalizedMessage());
		}
	}

	@Override
	public List<User> findAllById(List<Long> ids) {
		List<User> userList = userRepo.findAllById(ids);
		if(userList.isEmpty()) {
			throw new EntityNotFoundException("No users found for the given Ids.");
		}
		return userList;
	}
	
	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByMobileNo(String mobileNo) {
		try {
			validateMobileNumber(mobileNo);
		} catch (Exception ex) {
			
		}
		return null;
	}

	@Override
	public User save(User user) {
		
		if (user == null) {
	        throw new IllegalArgumentException("User object cannot be null.");
	    }
		if (isPasswordValid(user.getPassword())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			try {
				return userRepo.save(user);
			} catch(Exception ex) {
				throw new RuntimeException("An unexpected error occured.\n"+ex.getMessage());
			}
			
		}
		throw new RuntimeException("Invalid password. Password should contain at least one capital letter one small letter and one special character(@ $ ! % * ? &).");
		
	}

    public User findById(Long id) {
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }

        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found for ID: " + id));
    }


	@Override
	public boolean existsById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
        
		return userRepo.existsById(id);
	}

	@Override
	public long count() {
		return userRepo.count();
	}

	@Override
	public void deleteById(Long id) {
		if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer.");
        }
		userRepo.deleteById(id);
		
	}

	@Override
	public void delete(User user) {
		
		
	}

	@Override
	public void deleteAllById(List<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(List<? extends User> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends User> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends User> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> List<S> saveAllAndFlush(List<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(List<User> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(List<Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getReferenceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll(Example<User> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll(Example<User> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	//validation checks
	
	private void validateField(String field, String fieldName, String context) {
	    if (field == null || field.isBlank()) {
	        throw new IllegalArgumentException(context + ", " + fieldName + " should not be null or empty.");
	    }
	}

	private void validateEmail(String email) {
	    if (!isEmailValid(email)) {
	        throw new IllegalArgumentException(email + ": Email is not correctly patterned.");
	    }
	}

	private void validateMobileNumber(String mobileNo) {
	    if (!isMobileNumberValid(mobileNo)) {
	        throw new IllegalArgumentException("Mobile number " + mobileNo + " is not correct.");
	    }
	}
	
	private void validatePassword(String password) {
	    if (!isPasswordValid(password)) {
	        throw new IllegalArgumentException("Password should have at least one capital letter, one small letter, one number, one special character (@$!%*?&), and the length should be between 6 to 30.");
	    }
	}
	
	public static boolean isEmailValid(String email) { 
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean isMobileNumberValid(String mobileNumber) { 
		Pattern pattern = Pattern.compile(MOBILE_NUMBER_REGEX); 
		Matcher matcher = pattern.matcher(mobileNumber); 
		return matcher.matches(); 
	}
	
	public static boolean isPasswordValid(String password) { 
		Pattern pattern = Pattern.compile(PASSWORD_REGEX); 
		Matcher matcher = pattern.matcher(password); 
		return matcher.matches(); 
	}


	


}
