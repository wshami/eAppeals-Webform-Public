function dashboard(){var bb='',$=' top: -1000px;',yb='" for "gwt:onLoadErrorFn"',wb='" for "gwt:onPropertyErrorFn"',hb='");',zb='#',Ic='.cache.js',Bb='/',Hb='//',pc='433596CE0F344AC5C9D8E446AEED3448',tc='55F8577F5B300A95EC03EBAB7D8D16A2',uc='84FCDC7218EC2D8BBD9919F3F4921EC6',vc='9CA0528012F36E5BEFD81A4DDDE49BE2',Hc=':',qc=':1',wc=':10',xc=':11',rc=':2',sc=':3',yc=':4',zc=':5',Ac=':6',Bc=':7',Cc=':8',Dc=':9',qb='::',Pc=':moduleBase',ab='<!doctype html>',cb='<html><head><\/head><body><\/body><\/html>',tb='=',Ab='?',Ec='A848B854A4CDFD1733C2D37CD11BAA32',Fc='BC7A2A1B20687DCB8E6E269DB2D5F238',vb='Bad handler "',_='CSS1Compat',fb='Chrome',eb='DOMContentLoaded',V='DUMMY',Gc='F133C217F2C574B483648C888DD38C04',Oc='Ignoring non-whitelisted Dev Mode URL: ',Nc='__gwtDevModeHook:dashboard',bc='adobeair',cc='air',Gb='base',Eb='baseUrl',Q='begin',W='body',P='bootstrap',Jb='chrome',Db='clear.cache.gif',sb='content',T='dashboard',oc='dashboard.devmode.js',Fb='dashboard.nocache.js',pb='dashboard::',Kc='end',gb='eval("',Mc='file:',Zb='gecko',_b='gecko1_8',ac='gecko1_9',R='gwt.codesvr.dashboard=',S='gwt.codesvr=',xb='gwt:onLoadErrorFn',ub='gwt:onPropertyErrorFn',rb='gwt:property',Ib='gxt.user.agent',mb='head',Lc='http:',Mb='ie10',Sb='ie6',Qb='ie7',Ob='ie8',Nb='ie9',X='iframe',Cb='img',jb='javascript',Y='javascript:""',kc='linux',Jc='loadExternalRefs',jc='mac',ic='mac os x',hc='macintosh',nb='meta',lb='moduleRequested',kb='moduleStartup',Lb='msie',Rb='msie 6',Pb='msie 7',ob='name',Kb='opera',Z='position:absolute; width:0; height:0; border:none; left: -1000px;',$b='rv:1.8',Tb='safari',Vb='safari3',Xb='safari4',Yb='safari5',ib='script',nc='selectingPermutation',U='startup',db='undefined',fc='unknown',dc='user.agent',gc='user.agent.os',Ub='version/3',Wb='version/4',ec='webkit',mc='win32',lc='windows';var o=window;var p=document;r(P,Q);function q(){var a=o.location.search;return a.indexOf(R)!=-1||a.indexOf(S)!=-1}
function r(a,b){if(o.__gwtStatsEvent){o.__gwtStatsEvent({moduleName:T,sessionId:o.__gwtStatsSessionId,subSystem:U,evtGroup:a,millis:(new Date).getTime(),type:b})}}
dashboard.__sendStats=r;dashboard.__moduleName=T;dashboard.__errFn=null;dashboard.__moduleBase=V;dashboard.__softPermutationId=0;dashboard.__computePropValue=null;dashboard.__getPropMap=null;dashboard.__gwtInstallCode=function(){};dashboard.__gwtStartLoadingFragment=function(){return null};var s=function(){return false};var t=function(){return null};__propertyErrorFunction=null;var u=o.__gwt_activeModules=o.__gwt_activeModules||{};u[T]={moduleName:T};var v;function w(){B();return v}
function A(){B();return v.getElementsByTagName(W)[0]}
function B(){if(v){return}var a=p.createElement(X);a.src=Y;a.id=T;a.style.cssText=Z+$;a.tabIndex=-1;p.body.appendChild(a);v=a.contentDocument;if(!v){v=a.contentWindow.document}v.open();var b=document.compatMode==_?ab:bb;v.write(b+cb);v.close()}
function C(k){function l(a){function b(){if(typeof p.readyState==db){return typeof p.body!=db&&p.body!=null}return /loaded|complete/.test(p.readyState)}
var c=b();if(c){a();return}function d(){if(!c){c=true;a();if(p.removeEventListener){p.removeEventListener(eb,d,false)}if(e){clearInterval(e)}}}
if(p.addEventListener){p.addEventListener(eb,d,false)}var e=setInterval(function(){if(b()){d()}},50)}
function m(c){function d(a,b){a.removeChild(b)}
var e=A();var f=w();var g;if(navigator.userAgent.indexOf(fb)>-1&&window.JSON){var h=f.createDocumentFragment();h.appendChild(f.createTextNode(gb));for(var i=0;i<c.length;i++){var j=window.JSON.stringify(c[i]);h.appendChild(f.createTextNode(j.substring(1,j.length-1)))}h.appendChild(f.createTextNode(hb));g=f.createElement(ib);g.language=jb;g.appendChild(h);e.appendChild(g);d(e,g)}else{for(var i=0;i<c.length;i++){g=f.createElement(ib);g.language=jb;g.text=c[i];e.appendChild(g);d(e,g)}}}
dashboard.onScriptDownloaded=function(a){l(function(){m(a)})};r(kb,lb);var n=p.createElement(ib);n.src=k;p.getElementsByTagName(mb)[0].appendChild(n)}
dashboard.__startLoadingFragment=function(a){return G(a)};dashboard.__installRunAsyncCode=function(a){var b=A();var c=w().createElement(ib);c.language=jb;c.text=a;b.appendChild(c);b.removeChild(c)};function D(){var c={};var d;var e;var f=p.getElementsByTagName(nb);for(var g=0,h=f.length;g<h;++g){var i=f[g],j=i.getAttribute(ob),k;if(j){j=j.replace(pb,bb);if(j.indexOf(qb)>=0){continue}if(j==rb){k=i.getAttribute(sb);if(k){var l,m=k.indexOf(tb);if(m>=0){j=k.substring(0,m);l=k.substring(m+1)}else{j=k;l=bb}c[j]=l}}else if(j==ub){k=i.getAttribute(sb);if(k){try{d=eval(k)}catch(a){alert(vb+k+wb)}}}else if(j==xb){k=i.getAttribute(sb);if(k){try{e=eval(k)}catch(a){alert(vb+k+yb)}}}}}t=function(a){var b=c[a];return b==null?null:b};__propertyErrorFunction=d;dashboard.__errFn=e}
function F(){function e(a){var b=a.lastIndexOf(zb);if(b==-1){b=a.length}var c=a.indexOf(Ab);if(c==-1){c=a.length}var d=a.lastIndexOf(Bb,Math.min(c,b));return d>=0?a.substring(0,d+1):bb}
function f(a){if(a.match(/^\w+:\/\//)){}else{var b=p.createElement(Cb);b.src=a+Db;a=e(b.src)}return a}
function g(){var a=t(Eb);if(a!=null){return a}return bb}
function h(){var a=p.getElementsByTagName(ib);for(var b=0;b<a.length;++b){if(a[b].src.indexOf(Fb)!=-1){return e(a[b].src)}}return bb}
function i(){var a=p.getElementsByTagName(Gb);if(a.length>0){return a[a.length-1].href}return bb}
function j(){var a=p.location;return a.href==a.protocol+Hb+a.host+a.pathname+a.search+a.hash}
var k=g();if(k==bb){k=h()}if(k==bb){k=i()}if(k==bb&&j()){k=e(p.location.href)}k=f(k);return k}
function G(a){if(a.match(/^\//)){return a}if(a.match(/^[a-zA-Z]+:\/\//)){return a}return dashboard.__moduleBase+a}
function H(){var f=[];var g;function h(a,b){var c=f;for(var d=0,e=a.length-1;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
var i=[];var j=[];function k(a){var b=j[a](),c=i[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(__propertyErrorFunc){__propertyErrorFunc(a,d,b)}throw null}
j[Ib]=function(){var a=navigator.userAgent.toLowerCase();if(a.indexOf(Jb)!=-1)return Jb;if(a.indexOf(Kb)!=-1)return Kb;if(a.indexOf(Lb)!=-1){if(p.documentMode>=10)return Mb;if(p.documentMode>=9)return Nb;if(p.documentMode>=8)return Ob;if(a.indexOf(Pb)!=-1)return Qb;if(a.indexOf(Rb)!=-1)return Sb;return Mb}if(a.indexOf(Tb)!=-1){if(a.indexOf(Ub)!=-1)return Vb;if(a.indexOf(Wb)!=-1)return Xb;return Yb}if(a.indexOf(Zb)!=-1){if(a.indexOf($b)!=-1)return _b;return ac}if(a.indexOf(bc)!=-1)return cc;return null};i[Ib]={air:0,chrome:1,gecko1_8:2,gecko1_9:3,ie10:4,ie8:5,ie9:6,safari3:7,safari4:8,safari5:9};j[dc]=function(){var b=navigator.userAgent.toLowerCase();var c=function(a){return parseInt(a[1])*1000+parseInt(a[2])};if(function(){return b.indexOf(ec)!=-1}())return Tb;if(function(){return b.indexOf(Lb)!=-1&&p.documentMode>=10}())return Mb;if(function(){return b.indexOf(Lb)!=-1&&p.documentMode>=9}())return Nb;if(function(){return b.indexOf(Lb)!=-1&&p.documentMode>=8}())return Ob;if(function(){return b.indexOf(Zb)!=-1}())return _b;return fc};i[dc]={gecko1_8:0,ie10:1,ie8:2,ie9:3,safari:4};j[gc]=function(){var a=o.navigator.userAgent.toLowerCase();if(a.indexOf(hc)!=-1||a.indexOf(ic)!=-1){return jc}if(a.indexOf(kc)!=-1){return kc}if(a.indexOf(lc)!=-1||a.indexOf(mc)!=-1){return lc}return fc};i[gc]={linux:0,mac:1,unknown:2,windows:3};s=function(a,b){return b in i[a]};dashboard.__getPropMap=function(){var a={};for(var b in i){if(i.hasOwnProperty(b)){a[b]=k(b)}}return a};dashboard.__computePropValue=k;o.__gwt_activeModules[T].bindings=dashboard.__getPropMap;r(P,nc);if(q()){return G(oc)}var l;try{h([ac,_b,kc],pc);h([ac,_b,jc],pc+qc);h([ac,_b,fc],pc+rc);h([ac,_b,lc],pc+sc);h([_b,_b,kc],tc);h([_b,_b,jc],tc+qc);h([_b,_b,fc],tc+rc);h([_b,_b,lc],tc+sc);h([Ob,Ob,kc],uc);h([Ob,Ob,jc],uc+qc);h([Ob,Ob,fc],uc+rc);h([Ob,Ob,lc],uc+sc);h([cc,Tb,kc],vc);h([cc,Tb,jc],vc+qc);h([Yb,Tb,fc],vc+wc);h([Yb,Tb,lc],vc+xc);h([cc,Tb,fc],vc+rc);h([cc,Tb,lc],vc+sc);h([Jb,Tb,kc],vc+yc);h([Jb,Tb,jc],vc+zc);h([Jb,Tb,fc],vc+Ac);h([Jb,Tb,lc],vc+Bc);h([Yb,Tb,kc],vc+Cc);h([Yb,Tb,jc],vc+Dc);h([Nb,Nb,kc],Ec);h([Nb,Nb,jc],Ec+qc);h([Nb,Nb,fc],Ec+rc);h([Nb,Nb,lc],Ec+sc);h([Mb,Mb,kc],Fc);h([Mb,Mb,jc],Fc+qc);h([Mb,Mb,fc],Fc+rc);h([Mb,Mb,lc],Fc+sc);h([Vb,Tb,kc],Gc);h([Vb,Tb,jc],Gc+qc);h([Vb,Tb,fc],Gc+rc);h([Vb,Tb,lc],Gc+sc);h([Xb,Tb,kc],Gc+yc);h([Xb,Tb,jc],Gc+zc);h([Xb,Tb,fc],Gc+Ac);h([Xb,Tb,lc],Gc+Bc);l=f[k(Ib)][k(dc)][k(gc)];var m=l.indexOf(Hc);if(m!=-1){g=parseInt(l.substring(m+1),10);l=l.substring(0,m)}}catch(a){}dashboard.__softPermutationId=g;return G(l+Ic)}
function I(){if(!o.__gwt_stylesLoaded){o.__gwt_stylesLoaded={}}r(Jc,Q);r(Jc,Kc)}
D();dashboard.__moduleBase=F();u[T].moduleBase=dashboard.__moduleBase;var J=H();if(o){var K=!!(o.location.protocol==Lc||o.location.protocol==Mc);o.__gwt_activeModules[T].canRedirect=K;if(K){var L=Nc;var M=o.sessionStorage[L];if(!/^http:\/\/(localhost|127\.0\.0\.1)(:\d+)?\/.*$/.test(M)){if(M&&(window.console&&console.log)){console.log(Oc+M)}M=bb}if(M&&!o[L]){o[L]=true;o[L+Pc]=F();var N=p.createElement(ib);N.src=M;var O=p.getElementsByTagName(mb)[0];O.insertBefore(N,O.firstElementChild||O.children[0]);return false}}}I();r(P,Kc);C(J);return true}
dashboard.succeeded=dashboard();