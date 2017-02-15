var selenium = require('selenium-webdriver'),
    AxeBuilder = require('axe-webdriverjs'),
    Key = selenium.Key;

var util = require('util');

var driver;

var fs = require('fs');

describe('Checkout demo', function() {

    beforeEach(function(done) {
        driver = new selenium.Builder()
            .forBrowser('chrome')
            .build();

        driver.get('http://jp-dev.salientcrgt-devops.com/jpetstore/actions/Account.action?signonForm=')
            .then(function () {
                done();
            });
    });

    // Close website after each test is run (so it is opened fresh each time)
    afterEach(function(done) {
        driver.quit().then(function () {
            done();
        });
    });


    it('...should go to the checkout page and analyze it', function (done) {
	        driver.findElement(selenium.By.name("signon"))
		//driver.findElement(selenium.By.name("signon")).click();
		//driver.findElement(selenium.By.css("#SidebarContent > a > img")).click();
		//driver.findElement(selenium.By.linkText("FI-FW-01")).click(); 
		//driver.findElement(selenium.By.linkText("Add to Cart")).click();
		//driver.findElement(selenium.By.linkText("Proceed to Checkout")).click();
		//driver.findElement(selenium.By.id("Catalog"))
            .then(function () {
                AxeBuilder(driver)
                    .analyze(function(results) {
                        console.log('Accessibility Violations: ', results.violations.length);
                        if (results.violations.length > 0) {
                            //console.log(util.inspect(results.violations, true, null));  
		            var stream = fs.createWriteStream("testCheckoutPage.html");
       			    stream.once('open', function(fd) {
        			for (i=0; i<results.violations.length; i++) {
         				var violation_num = i+1;
					stream.write("<div style='color:#0000FF'>Violation #" + violation_num + "/" + results.violations.length + "</div>\n");
         				stream.write("Help: " + results.violations[i].help + "<br/>\n");
					//stream.write("<a href=''" + results.violations[i].helpUrl + "''>More Info</a><br/>\n");
         				stream.write("Description:  " + results.violations[i].description + "<br/>\n");
         				stream.write("Overall impact:  " + results.violations[i].impact + "<br/>\n");
					stream.write("<br/>\n");
         				for (j=0; j< results.violations[i].nodes.length; j++) {
						var error_num = j+1;
						stream.write("<div style='color:red'>&nbsp;&nbsp;&nbsp;&nbsp;Error #" + error_num + "/" + results.violations[i].nodes.length + "</div>\n");
						var myHtml = results.violations[i].nodes[j].html;
         					myHtml = myHtml.split("<").join("&lt;");
         					myHtml = myHtml.split(">").join("&gt;");
         					stream.write("&nbsp;&nbsp;&nbsp;&nbsp;HTML:  " + myHtml + "<br/>\n");
						for (k=0; k< results.violations[i].nodes[j].any.length; k++) {
							stream.write("&nbsp;&nbsp;&nbsp;&nbsp;Impact:  " + results.violations[i].nodes[j].any[k].impact + "<br/>\n");
          						stream.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Message:  " + results.violations[i].nodes[j].any[k].message + "<br/>\n");
						}
						stream.write("<br/>\n");
					}
					stream.write("<hr/>\n");
        			}
        			stream.end();
			    });
                        }
                        expect(results.violations.length).toBe(0);
                        done();
                    })
            });
    });
});