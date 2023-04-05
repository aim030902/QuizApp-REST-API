package uz.aim.quizapprestapi.services.oauth2;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import uz.aim.quizapprestapi.dtos.oauth2.OAuth2UserDTO;

/**
 * @author : Abbosbek Murodov
 * @since : 02/04/23 / 12:05
 * Project : Trello-REST-API / IntelliJ IDEA
 */

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user =  super.loadUser(userRequest);

        return new OAuth2UserDTO(user);
    }

}