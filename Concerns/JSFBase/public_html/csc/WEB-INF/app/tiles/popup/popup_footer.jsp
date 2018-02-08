<%@ page contentType="text/html;charset=UTF-8"%>
<div class="divFooter Footer" id="footer_div" onmousedown="hideNavigationMenu();" >
            <span>جميع الحقوق محفوظة لديوان الخدمة المدنية © 2015</span>

<script type="text/javascript"> 
function fixA4J() {
                var bodies = document.getElementsByTagName("body");                
                if (bodies.length > 1) {
                   for (var i = 0; i < bodies.length; i++) {                        
                        if (bodies[i].firstChild == null) {
                            bodies[i].parentNode.removeChild(bodies[i])
                            break;
                        }
                    }
                    var heads = document.getElementsByTagName("head");
                    for (var i = 0; i < heads.length; i++) {                        
                        if (heads[i].children.length == 1) {
                            var s = heads[i].firstChild;
                            heads[i].parentNode.removeChild(heads[i])
                            document.head.appendChild(s);
                            break;
                        }
                    }
                }
            }
            
            window.setTimeout(fixA4J, 100);
</script>

</div>
