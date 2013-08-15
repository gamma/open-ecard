/****************************************************************************
 * Copyright (C) 2012 HS Coburg.
 * All rights reserved.
 * Contact: ecsec GmbH (info@ecsec.de)
 *
 * This file is part of the Open eCard App.
 *
 * GNU General Public License Usage
 * This file may be used under the terms of the GNU General Public
 * License version 3.0 as published by the Free Software Foundation
 * and appearing in the file LICENSE.GPL included in the packaging of
 * this file. Please review the following information to ensure the
 * GNU General Public License version 3.0 requirements will be met:
 * http://www.gnu.org/copyleft/gpl.html.
 *
 * Other Usage
 * Alternatively, this file may be used in accordance with the terms
 * and conditions contained in a signed written agreement between
 * you and ecsec GmbH.
 *
 ***************************************************************************/

package org.openecard.sal.protocol.pincompare;

import org.openecard.addon.ActionInitializationException;
import org.openecard.addon.Context;
import org.openecard.addon.sal.FunctionType;
import org.openecard.addon.sal.SALProtocolBaseImpl;


/**
 * Implements the PIN Compare protocol.
 * See TR-03112, version 1.1.2, part 7, section 4.
 *
 * @author Dirk Petrautzki <petrautzki@hs-coburg.de>
 */
public class PINCompareProtocol extends SALProtocolBaseImpl {

    @Override
    public boolean hasNextStep(FunctionType functionName) {
	for (int i = 0; i < steps.size(); i++) {
	    if (steps.get(i).getFunctionType().equals(functionName)) {
		super.curStep = i;
		return true;
	    }
	}
	return false;
    }

    @Override
    public void init(Context ctx) throws ActionInitializationException {
	steps.add(new DIDCreateStep(ctx.getDispatcher()));
	steps.add(new DIDUpdateStep(ctx.getDispatcher()));
	steps.add(new DIDGetStep());
	steps.add(new DIDAuthenticateStep(ctx.getDispatcher()));
    }

    @Override
    public void destroy() {
	// nothing to see here ... move along
    }

}