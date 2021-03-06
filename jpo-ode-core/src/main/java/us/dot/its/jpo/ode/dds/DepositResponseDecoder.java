/*******************************************************************************
 * Copyright (c) 2015 US DOT - Joint Program Office
 *
 * The Government has unlimited rights to all documents/material produced under 
 * this task order. All documents and materials, to include the source code of 
 * any software produced under this contract, shall be Government owned and the 
 * property of the Government with all rights and privileges of ownership/copyright 
 * belonging exclusively to the Government. These documents and materials may 
 * not be used or sold by the Contractor without written permission from the CO.
 * All materials supplied to the Government shall be the sole property of the 
 * Government and may not be used for any other purpose. This right does not 
 * abrogate any other Government rights.
 *
 * Contributors:
 *     Booz | Allen | Hamilton - initial API and implementation
 *******************************************************************************/
package us.dot.its.jpo.ode.dds;

import javax.websocket.DecodeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.dot.its.jpo.ode.eventlog.EventLogger;

public class DepositResponseDecoder extends DdsStatusMessageDecoder {

   private static final Logger logger = LoggerFactory
         .getLogger(DepositResponseDecoder.class);

   @Override
   public DdsMessage decode(String message) throws DecodeException {
      DdsMessage statusMsg;
      statusMsg = super.decode(message);
      logger.info("Deposit Response Received: {}", message);
      EventLogger.logger.info("Deposit Response Received: {}", message);

      return statusMsg;
   }

   @Override
   public boolean willDecode(String message) {
      return true;
   }

}
