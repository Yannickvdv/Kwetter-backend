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
package common.response;

import java.io.Serializable;
import javax.ws.rs.core.Response.Status;
import lombok.Getter;

/**
 *
 * @author Yannick
 */
@Getter
public class ResponseMessage implements Serializable {

    private final boolean ok;

    private final String status;

    private final int code;

    private final String message;

    public ResponseMessage(Status status, String message) {
        this.status = status.getReasonPhrase();
        this.message = message;
        this.code = status.getStatusCode();
        this.ok = status == Status.OK || status == Status.CREATED;
    }

}