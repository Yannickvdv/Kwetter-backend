/*
 * Copyright (C) 2019 Yannick
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package service;

import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import lombok.Getter;

/**
 *
 * @author Yannick
 */
public class KeyGenerator {

    private static final String SECRET = "WjDOQn8CoeU7F0jr5nLL8OMe";

    @Getter
    private static final byte[] API_KEY_SECRET_BYTES = DatatypeConverter.parseBase64Binary(KeyGenerator.SECRET);
    @Getter
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    @Getter
    private static final Key KEY = new SecretKeySpec(KeyGenerator.API_KEY_SECRET_BYTES, KeyGenerator.SIGNATURE_ALGORITHM.getJcaName());

    private KeyGenerator() {
    }
}
