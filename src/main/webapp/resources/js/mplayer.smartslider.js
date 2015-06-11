$(document).ready(function() {
    $maxval = document.getElementById('ex12c').value;
    $val1 = 0;
    if (document.getElementById('minsize').value.length != 0)
        $val1 = document.getElementById('minsize').value;
    $val2 = $maxval;
    if (document.getElementById('maxsize').value.length != 0)
        $val2 = document.getElementById('maxsize').value;
    if ($val2 > $maxval)
        $val2 = $maxval;
    if ($val2 == 0)
        $val2 = $maxval;
    if ($val2 != $maxval)
        document.getElementById('maxsize').value = $val2 + ' Kb'
    if ($val1 != 0)
        document.getElementById('minsize').value = $val1 + ' Kb'
    document.getElementById('maxsize').value = '';
    $('#ex12c').slider({ id: "slider12c", min: 0, max: $maxval, range: true, value: [$val1, $val2] }).on('slide', function(ev){
        if (ev.value[0] == 0)
            document.getElementById('minsize').value = '';
        else
            document.getElementById('minsize').value = ev.value[0] + ' Kb';
        if (ev.value[1] == $maxval)
            document.getElementById('maxsize').value = '';
        else
            document.getElementById('maxsize').value = ev.value[1] + ' Kb';
    });
    
    $('#createdatefrom').datepicker({
        format: "dd.mm.yyyy"});
    $('#createdateto').datepicker({
        format: "dd.mm.yyyy"});
    $('#changedatefrom').datepicker({
        format: "dd.mm.yyyy"});
    $('#changedateto').datepicker({
        format: "dd.mm.yyyy"});
});